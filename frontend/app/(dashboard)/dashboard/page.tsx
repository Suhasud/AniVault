"use client";

import { useQuery } from "@tanstack/react-query";
import { animeService } from "@/services/anime.service";

export default function DashboardPage() {
    const { data, isLoading, isError } = useQuery({
        queryKey: ["dashboard-anime"],
        queryFn: () =>
            animeService.getAll({
                page: 0,
                size: 100,
            }),
    });

    if (isLoading) {
        return (
            <div className="p-8">
                <p className="text-gray-400">
                    Loading dashboard...
                </p>
            </div>
        );
    }

    if (isError) {
        return (
            <div className="p-8">
                <p className="text-red-400">
                    Failed to load dashboard.
                </p>
            </div>
        );
    }

    const anime = data?.content ?? [];

    const totalAnime = anime.length;

    const watching = anime.filter(
        (item) => item.watchStatus === "WATCHING",
    ).length;

    const completed = anime.filter(
        (item) => item.watchStatus === "COMPLETED",
    ).length;

    const planning = anime.filter(
        (item) => item.watchStatus === "PLANNING",
    ).length;

    const totalEpisodes = anime.reduce(
        (total, item) => total + item.episodes,
        0,
    );

    const watchedEpisodes = anime.reduce(
        (total, item) => total + item.watchedEpisodes,
        0,
    );

    const overallProgress =
        totalEpisodes > 0
            ? Math.round(
                (watchedEpisodes / totalEpisodes) * 100,
            )
            : 0;

    const currentlyWatching = anime.filter(
        (item) => item.watchStatus === "WATCHING",
    );

    return (
        <div className="p-6 md:p-8">
            {/* Header */}
            <div className="mb-8">
                <h1 className="text-3xl font-bold">
                    Dashboard
                </h1>

                <p className="mt-2 text-gray-400">
                    Your AniVault overview.
                </p>
            </div>

            {/* Statistics */}
            <div className="grid gap-5 sm:grid-cols-2 lg:grid-cols-4">
                <StatCard
                    title="Total Anime"
                    value={totalAnime}
                    icon="🎬"
                />

                <StatCard
                    title="Watching"
                    value={watching}
                    icon="👀"
                />

                <StatCard
                    title="Completed"
                    value={completed}
                    icon="✅"
                />

                <StatCard
                    title="Planning"
                    value={planning}
                    icon="📚"
                />
            </div>

            {/* Overall Progress */}
            <div className="mt-8 rounded-xl border border-gray-800 bg-gray-900 p-6">
                <div className="flex items-center justify-between">
                    <div>
                        <h2 className="text-lg font-semibold">
                            Overall Progress
                        </h2>

                        <p className="mt-1 text-sm text-gray-400">
                            {watchedEpisodes} of {totalEpisodes} episodes watched
                        </p>
                    </div>

                    <span className="text-2xl font-bold">
            {overallProgress}%
          </span>
                </div>

                <div className="mt-5 h-3 overflow-hidden rounded-full bg-gray-800">
                    <div
                        className="h-full rounded-full bg-purple-600"
                        style={{
                            width: `${Math.min(overallProgress, 100)}%`,
                        }}
                    />
                </div>
            </div>

            {/* Currently Watching */}
            <div className="mt-8">
                <h2 className="mb-4 text-xl font-semibold">
                    Currently Watching
                </h2>

                {currentlyWatching.length === 0 ? (
                    <div className="rounded-xl border border-gray-800 bg-gray-900 p-6">
                        <p className="text-gray-400">
                            You're not currently watching anything.
                        </p>
                    </div>
                ) : (
                    <div className="grid gap-5 md:grid-cols-2 lg:grid-cols-3">
                        {currentlyWatching.map((item) => {
                            const progress =
                                item.episodes > 0
                                    ? Math.round(
                                        (item.watchedEpisodes /
                                            item.episodes) *
                                        100,
                                    )
                                    : 0;

                            return (
                                <div
                                    key={item.id}
                                    className="rounded-xl border border-gray-800 bg-gray-900 p-5"
                                >
                                    <div className="flex items-center justify-between">
                                        <h3 className="truncate font-semibold">
                                            {item.title}
                                        </h3>

                                        <span className="ml-3 text-sm text-gray-400">
                      {progress}%
                    </span>
                                    </div>

                                    <p className="mt-1 text-sm text-gray-500">
                                        {item.studio}
                                    </p>

                                    <div className="mt-4 h-2 overflow-hidden rounded-full bg-gray-800">
                                        <div
                                            className="h-full rounded-full bg-purple-600"
                                            style={{
                                                width: `${Math.min(
                                                    progress,
                                                    100,
                                                )}%`,
                                            }}
                                        />
                                    </div>

                                    <p className="mt-2 text-sm text-gray-400">
                                        {item.watchedEpisodes}/
                                        {item.episodes} episodes
                                    </p>
                                </div>
                            );
                        })}
                    </div>
                )}
            </div>
        </div>
    );
}

function StatCard({
                      title,
                      value,
                      icon,
                  }: {
    title: string;
    value: number;
    icon: string;
}) {
    return (
        <div className="rounded-xl border border-gray-800 bg-gray-900 p-5">
            <div className="flex items-center justify-between">
                <p className="text-sm text-gray-400">
                    {title}
                </p>

                <span className="text-2xl">{icon}</span>
            </div>

            <p className="mt-4 text-3xl font-bold">
                {value}
            </p>
        </div>
    );
}