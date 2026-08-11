"use client";

import { useRouter } from "next/navigation";

export default function Sidebar() {
    const router = useRouter();

    return (
        <aside className="hidden w-60 shrink-0 border-r border-gray-800 bg-gray-950 p-4 md:block">
            <nav className="space-y-2">
                <button
                    onClick={() => router.push("/dashboard")}
                    className="w-full rounded-lg px-4 py-3 text-left text-gray-300 transition hover:bg-gray-800 hover:text-white"
                >
                    📊 Dashboard
                </button>
                <button
                    onClick={() => router.push("/anime")}
                    className="w-full rounded-lg px-4 py-3 text-left text-gray-300 transition hover:bg-gray-800 hover:text-white"
                >
                    🎬 Anime
                </button>

                <button
                    onClick={() => router.push("/anime/new")}
                    className="w-full rounded-lg px-4 py-3 text-left text-gray-300 transition hover:bg-gray-800 hover:text-white"
                >
                    ➕ Add Anime
                </button>
            </nav>
        </aside>
    );
}