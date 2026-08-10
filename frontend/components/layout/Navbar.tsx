"use client";

import { useRouter } from "next/navigation";
import { useAuthStore } from "@/store/auth.store";

export default function Navbar() {
    const router = useRouter();
    const clearToken = useAuthStore((state) => state.clearToken);

    const handleLogout = () => {
        clearToken();
        router.push("/login");
    };

    return (
        <header className="flex h-16 items-center justify-between border-b border-gray-800 bg-gray-950 px-6">
            <button
                onClick={() => router.push("/anime")}
                className="text-xl font-bold text-white"
            >
                🎌 AniVault
            </button>

            <button
                onClick={handleLogout}
                className="rounded-lg px-4 py-2 text-sm font-medium text-gray-300 transition hover:bg-gray-800 hover:text-white"
            >
                Logout
            </button>
        </header>
    );
}