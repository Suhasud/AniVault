"use client";

import { useState } from "react";
import { useRouter } from "next/navigation";
import { useQuery } from "@tanstack/react-query";
import { animeService } from "@/services/anime.service";

export default function AnimePage() {
    const router = useRouter();

    const [title, setTitle] = useState("");
    const [studio, setStudio] = useState("");
    const [genre, setGenre] = useState("");
    const [watchStatus, setWatchStatus] = useState("");
    const [animeStatus, setAnimeStatus] = useState("");
    const [sort, setSort] = useState("title,asc");
    const [page, setPage] = useState(0);

    const [deletingId, setDeletingId] = useState<number | null>(
        null,
    );

    const size = 8;

    const {
        data,
        isLoading,
        isError,
        isFetching,
        refetch,
    } = useQuery({
        queryKey: [
            "anime",
            title,
            studio,
            genre,
            watchStatus,
            animeStatus,
            sort,
            page,
        ],

        queryFn: () =>
            animeService.getAll({
                title: title || undefined,
                studio: studio || undefined,
                genre: genre || undefined,
                watchStatus: watchStatus || undefined,
                animeStatus: animeStatus || undefined,
                page,
                size,
                sort,
            }),
    });

    const resetPage = () => {
        setPage(0);
    };

    const handleDelete = async (id: number) => {
        const confirmed = window.confirm(
            "Are you sure you want to delete this anime?",
        );

        if (!confirmed) {
            return;
        }

        try {
            setDeletingId(id);

            await animeService.delete(id);

            await refetch();
        } catch (error) {
            console.error("Failed to delete anime:", error);

            window.alert(
                "Failed to delete anime. Make sure you have admin access.",
            );
        } finally {
            setDeletingId(null);
        }
    };

    if (isLoading) {
        return (
            <div className="p-8">
                <p className="text-gray-400">
                    Loading anime...
                </p>
            </div>
        );
    }

    if (isError) {
        return (
            <div className="p-8">
                <p className="text-red-400">
                    Failed to load anime.
                </p>
            </div>
        );
    }

    return (
        <div className="p-6 md:p-8">
            {/* Header */}
            <div className="mb-8 flex flex-col gap-4 sm:flex-row sm:items-center sm:justify-between">
                <div>
                    <h1 className="text-3xl font-bold">
                        My Anime
                    </h1>

                    <p className="mt-2 text-gray-400">
                        Search and manage your anime watchlist.
                    </p>
                </div>

                <button
                    onClick={() => router.push("/anime/new")}
                    className="rounded-lg bg-purple-600 px-5 py-3 font-medium text-white transition hover:bg-purple-700"
                >
                    + Add Anime
                </button>
            </div>

            {/* Filters */}
            <div className="mb-8 grid gap-4 md:grid-cols-2 lg:grid-cols-3">
                {/* Title */}
                <input
                    type="text"
                    placeholder="Search by title..."
                    value={title}
                    onChange={(event) => {
                        setTitle(event.target.value);
                        resetPage();
                    }}
                    className="rounded-lg border border-gray-700 bg-gray-900 px-4 py-3 text-white outline-none focus:border-purple-500"
                />

                {/* Studio */}
                <input
                    type="text"
                    placeholder="Search by studio..."
                    value={studio}
                    onChange={(event) => {
                        setStudio(event.target.value);
                        resetPage();
                    }}
                    className="rounded-lg border border-gray-700 bg-gray-900 px-4 py-3 text-white outline-none focus:border-purple-500"
                />

                {/* Genre */}
                <input
                    type="text"
                    placeholder="Search by genre..."
                    value={genre}
                    onChange={(event) => {
                        setGenre(event.target.value);
                        resetPage();
                    }}
                    className="rounded-lg border border-gray-700 bg-gray-900 px-4 py-3 text-white outline-none focus:border-purple-500"
                />

                {/* Watch Status */}
                <select
                    value={watchStatus}
                    onChange={(event) => {
                        setWatchStatus(event.target.value);
                        resetPage();
                    }}
                    className="rounded-lg border border-gray-700 bg-gray-900 px-4 py-3 text-white outline-none"
                >
                    <option value="">
                        All Watch Status
                    </option>

                    <option value="PLANNING">
                        Planning
                    </option>

                    <option value="WATCHING">
                        Watching
                    </option>

                    <option value="COMPLETED">
                        Completed
                    </option>

                    <option value="DROPPED">
                        Dropped
                    </option>
                </select>

                {/* Anime Status */}
                <select
                    value={animeStatus}
                    onChange={(event) => {
                        setAnimeStatus(event.target.value);
                        resetPage();
                    }}
                    className="rounded-lg border border-gray-700 bg-gray-900 px-4 py-3 text-white outline-none"
                >
                    <option value="">
                        All Anime Status
                    </option>

                    <option value="ONGOING">
                        Ongoing
                    </option>

                    <option value="COMPLETED">
                        Completed
                    </option>

                    <option value="UPCOMING">
                        Upcoming
                    </option>
                </select>

                {/* Sorting */}
                <select
                    value={sort}
                    onChange={(event) => {
                        setSort(event.target.value);
                        resetPage();
                    }}
                    className="rounded-lg border border-gray-700 bg-gray-900 px-4 py-3 text-white outline-none"
                >
                    <option value="title,asc">
                        Title A → Z
                    </option>

                    <option value="title,desc">
                        Title Z → A
                    </option>

                    <option value="episodes,asc">
                        Episodes Low → High
                    </option>

                    <option value="episodes,desc">
                        Episodes High → Low
                    </option>
                </select>
            </div>

            {/* Fetching indicator */}
            {isFetching && (
                <p className="mb-4 text-sm text-gray-500">
                    Updating...
                </p>
            )}

            {/* Empty state */}
            {data?.content.length === 0 ? (
                <div className="rounded-xl border border-gray-800 bg-gray-900 p-10 text-center">
                    <p className="text-gray-400">
                        No anime found.
                    </p>
                </div>
            ) : (
                /* Anime Grid */
                <div className="grid gap-5 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4">
                    {data?.content.map((anime) => {
                        const progress =
                            anime.episodes > 0
                                ? Math.round(
                                    (anime.watchedEpisodes /
                                        anime.episodes) *
                                    100,
                                )
                                : 0;

                        const safeProgress = Math.min(
                            Math.max(progress, 0),
                            100,
                        );

                        return (
                            <article
                                key={anime.id}
                                className="group overflow-hidden rounded-2xl border border-gray-800 bg-gray-900 shadow-lg transition duration-300 hover:-translate-y-1 hover:border-purple-500/50 hover:shadow-purple-950/20"
                            >
                                {/* Poster */}
                                <div className="relative h-72 w-full overflow-hidden bg-gray-800">
                                    {anime.imageUrl ? (
                                        <img
                                            src={anime.imageUrl}
                                            alt={`${anime.title} poster`}
                                            className="h-full w-full object-cover transition duration-500 group-hover:scale-105"
                                        />
                                    ) : (
                                        <div className="flex h-full items-center justify-center">
                                            <span className="text-5xl">🎬</span>
                                        </div>
                                    )}

                                    {/* Poster overlay */}
                                    <div className="absolute inset-x-0 bottom-0 h-24 bg-gradient-to-t from-gray-900 to-transparent" />
                                </div>

                                <div className="p-5">
                                    {/* Title */}
                                    <h2
                                        className="truncate text-lg font-semibold text-white"
                                        title={anime.title}
                                    >
                                        {anime.title}
                                    </h2>

                                    {/* Studio */}
                                    <p className="mt-1 truncate text-sm text-gray-400">
                                        {anime.studio}
                                    </p>

                                    {/* Genres */}
                                    <p
                                        className="mt-2 truncate text-sm text-gray-500"
                                        title={anime.genres.join(", ")}
                                    >
                                        {anime.genres.join(" • ")}
                                    </p>

                                    {/* Episode Progress */}
                                    <div className="mt-5 flex items-center justify-between text-sm">
            <span className="text-gray-400">
                {anime.watchedEpisodes}/{anime.episodes} episodes
            </span>

                                        <span className="font-medium text-purple-400">
                {safeProgress}%
            </span>
                                    </div>

                                    <div className="mt-2 h-2 overflow-hidden rounded-full bg-gray-800">
                                        <div
                                            className="h-full rounded-full bg-purple-600 transition-all duration-500"
                                            style={{
                                                width: `${safeProgress}%`,
                                            }}
                                        />
                                    </div>

                                    {/* Status */}
                                    <div className="mt-5 flex flex-wrap gap-2">
            <span className="rounded-full bg-purple-500/10 px-3 py-1 text-xs font-medium text-purple-400">
                {anime.watchStatus}
            </span>

                                        <span className="rounded-full bg-gray-800 px-3 py-1 text-xs font-medium text-gray-400">
                {anime.animeStatus}
            </span>
                                    </div>

                                    {/* Actions */}
                                    <div className="mt-5 flex gap-2">
                                        <button
                                            onClick={() =>
                                                router.push(`/anime/${anime.id}/edit`)
                                            }
                                            className="flex-1 rounded-lg border border-gray-700 px-4 py-2 text-sm font-medium text-gray-300 transition hover:border-purple-500 hover:bg-purple-500/10 hover:text-white"
                                        >
                                            Edit
                                        </button>

                                        <button
                                            onClick={() => handleDelete(anime.id)}
                                            disabled={deletingId === anime.id}
                                            className="flex-1 rounded-lg border border-red-900/70 px-4 py-2 text-sm font-medium text-red-400 transition hover:bg-red-950 disabled:cursor-not-allowed disabled:opacity-50"
                                        >
                                            {deletingId === anime.id
                                                ? "Deleting..."
                                                : "Delete"}
                                        </button>
                                    </div>
                                </div>
                            </article>
                        );
                    })}
                </div>
            )}

            {/* Pagination */}
            <div className="mt-8 flex items-center justify-between">
                <button
                    disabled={page === 0}
                    onClick={() =>
                        setPage((current) => current - 1)
                    }
                    className="rounded-lg border border-gray-700 px-4 py-2 text-sm text-gray-300 transition hover:bg-gray-800 disabled:cursor-not-allowed disabled:opacity-40"
                >
                    ← Previous
                </button>

                <span className="text-sm text-gray-400">
          Page {page + 1} of{" "}
                    {data?.totalPages ?? 1}
        </span>

                <button
                    disabled={
                        page >=
                        (data?.totalPages ?? 1) - 1
                    }
                    onClick={() =>
                        setPage((current) => current + 1)
                    }
                    className="rounded-lg border border-gray-700 px-4 py-2 text-sm text-gray-300 transition hover:bg-gray-800 disabled:cursor-not-allowed disabled:opacity-40"
                >
                    Next →
                </button>
            </div>
        </div>
    );
}