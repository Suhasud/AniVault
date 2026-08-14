"use client";

import { FormEvent, useState } from "react";
import { useRouter } from "next/navigation";
import { useQueryClient } from "@tanstack/react-query";
import {
    animeService,
    AnimeRequest,
} from "@/services/anime.service";
import { useAuthStore } from "@/store/auth.store";

export default function NewAnimePage() {
    const router = useRouter();
    const queryClient = useQueryClient();
    const token = useAuthStore((state) => state.token);

    const [form, setForm] = useState<AnimeRequest>({
        title: "",
        genres: [],
        studio: "",
        episodes: 1,
        watchedEpisodes: 0,
        animeStatus: "ONGOING",
        watchStatus: "PLANNING",
    });

    const [genreInput, setGenreInput] = useState("");
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");

    const handleChange = (
        event: React.ChangeEvent<
            HTMLInputElement | HTMLSelectElement
        >,
    ) => {
        const { name, value } = event.target;

        setForm((current) => ({
            ...current,
            [name]:
                name === "episodes" || name === "watchedEpisodes"
                    ? Number(value)
                    : value,
        }));
    };

    const addGenre = () => {
        const genre = genreInput.trim();

        if (!genre) {
            return;
        }

        if (form.genres.includes(genre)) {
            return;
        }

        setForm((current) => ({
            ...current,
            genres: [...current.genres, genre],
        }));

        setGenreInput("");
    };

    const removeGenre = (genreToRemove: string) => {
        setForm((current) => ({
            ...current,
            genres: current.genres.filter(
                (genre) => genre !== genreToRemove,
            ),
        }));
    };

    const handleSubmit = async (
        event: FormEvent<HTMLFormElement>,
    ) => {
        event.preventDefault();

        setError("");
        setLoading(true);

        try {
            await animeService.create(form);

            await queryClient.invalidateQueries({
                queryKey: ["anime", token],
            });

            await queryClient.invalidateQueries({
                queryKey: ["dashboard-anime", token],
            });

            router.push("/anime");
        } catch (error) {
            console.error("Failed to create anime:", error);
            setError(
                "Failed to create anime. Try again",
            );
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="p-6 md:p-8">
            <div className="mb-8">
                <h1 className="text-3xl font-bold">
                    Add Anime
                </h1>

                <p className="mt-2 text-gray-400">
                    Add a new anime to your watchlist.
                </p>
            </div>

            <form
                onSubmit={handleSubmit}
                className="max-w-3xl space-y-6 rounded-xl border border-gray-800 bg-gray-900 p-6"
            >
                {/* Title */}
                <div>
                    <label className="mb-2 block text-sm font-medium text-gray-300">
                        Title
                    </label>

                    <input
                        name="title"
                        value={form.title}
                        onChange={handleChange}
                        required
                        placeholder="e.g. One Piece"
                        className="w-full rounded-lg border border-gray-700 bg-gray-800 px-4 py-3 text-white outline-none focus:border-purple-500"
                    />
                </div>

                {/* Studio */}
                <div>
                    <label className="mb-2 block text-sm font-medium text-gray-300">
                        Studio
                    </label>

                    <input
                        name="studio"
                        value={form.studio}
                        onChange={handleChange}
                        required
                        placeholder="e.g. Toei Animation"
                        className="w-full rounded-lg border border-gray-700 bg-gray-800 px-4 py-3 text-white outline-none focus:border-purple-500"
                    />
                </div>

                {/* Genres */}
                <div>
                    <label className="mb-2 block text-sm font-medium text-gray-300">
                        Genres
                    </label>

                    <div className="flex gap-2">
                        <input
                            value={genreInput}
                            onChange={(event) =>
                                setGenreInput(event.target.value)
                            }
                            onKeyDown={(event) => {
                                if (event.key === "Enter") {
                                    event.preventDefault();
                                    addGenre();
                                }
                            }}
                            placeholder="e.g. Action"
                            className="flex-1 rounded-lg border border-gray-700 bg-gray-800 px-4 py-3 text-white outline-none focus:border-purple-500"
                        />

                        <button
                            type="button"
                            onClick={addGenre}
                            className="rounded-lg bg-gray-700 px-5 py-3 font-medium text-white hover:bg-gray-600"
                        >
                            Add
                        </button>
                    </div>

                    <div className="mt-3 flex flex-wrap gap-2">
                        {form.genres.map((genre) => (
                            <button
                                key={genre}
                                type="button"
                                onClick={() => removeGenre(genre)}
                                className="rounded-full bg-purple-600/20 px-3 py-1 text-sm text-purple-300"
                            >
                                {genre} ×
                            </button>
                        ))}
                    </div>
                </div>

                {/* Episodes */}
                <div className="grid gap-4 md:grid-cols-2">
                    <div>
                        <label className="mb-2 block text-sm font-medium text-gray-300">
                            Episodes
                        </label>

                        <input
                            name="episodes"
                            type="number"
                            min="1"
                            value={form.episodes}
                            onChange={handleChange}
                            required
                            className="w-full rounded-lg border border-gray-700 bg-gray-800 px-4 py-3 text-white outline-none focus:border-purple-500"
                        />
                    </div>

                    <div>
                        <label className="mb-2 block text-sm font-medium text-gray-300">
                            Watched Episodes
                        </label>

                        <input
                            name="watchedEpisodes"
                            type="number"
                            min="0"
                            value={form.watchedEpisodes}
                            onChange={handleChange}
                            required
                            className="w-full rounded-lg border border-gray-700 bg-gray-800 px-4 py-3 text-white outline-none focus:border-purple-500"
                        />
                    </div>
                </div>

                {/* Status */}
                <div className="grid gap-4 md:grid-cols-2">
                    <div>
                        <label className="mb-2 block text-sm font-medium text-gray-300">
                            Anime Status
                        </label>

                        <select
                            name="animeStatus"
                            value={form.animeStatus}
                            onChange={handleChange}
                            className="w-full rounded-lg border border-gray-700 bg-gray-800 px-4 py-3 text-white outline-none"
                        >
                            <option value="ONGOING">Ongoing</option>
                            <option value="COMPLETED">Completed</option>
                            <option value="UPCOMING">Upcoming</option>
                        </select>
                    </div>

                    <div>
                        <label className="mb-2 block text-sm font-medium text-gray-300">
                            Watch Status
                        </label>

                        <select
                            name="watchStatus"
                            value={form.watchStatus}
                            onChange={handleChange}
                            className="w-full rounded-lg border border-gray-700 bg-gray-800 px-4 py-3 text-white outline-none"
                        >
                            <option value="PLANNING">Planning</option>
                            <option value="WATCHING">Watching</option>
                            <option value="COMPLETED">Completed</option>
                            <option value="DROPPED">Dropped</option>
                        </select>
                    </div>
                </div>

                {error && (
                    <div className="rounded-lg bg-red-500/10 p-4 text-sm text-red-400">
                        {error}
                    </div>
                )}

                <div className="flex gap-3">
                    <button
                        type="button"
                        onClick={() => router.push("/anime")}
                        className="rounded-lg border border-gray-700 px-5 py-3 font-medium text-gray-300 hover:bg-gray-800"
                    >
                        Cancel
                    </button>

                    <button
                        type="submit"
                        disabled={loading}
                        className="rounded-lg bg-purple-600 px-5 py-3 font-medium text-white hover:bg-purple-700 disabled:cursor-not-allowed disabled:opacity-50"
                    >
                        {loading ? "Creating..." : "Create Anime"}
                    </button>
                </div>
            </form>
        </div>
    );
}