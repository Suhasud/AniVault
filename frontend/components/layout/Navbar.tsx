"use client";

import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";
import { useAuthStore } from "@/store/auth.store";

export default function Navbar() {
    const router = useRouter();
    const clearToken = useAuthStore((state) => state.clearToken);

    const [username, setUsername] = useState<string | null>(null);

    useEffect(() => {
        const token = localStorage.getItem("anivault_token");

        if (!token) {
            return;
        }

        try {
            const payload = JSON.parse(
                atob(token.split(".")[1])
            );

            setUsername(payload.sub ?? null);
        } catch (error) {
            console.error(
                "Failed to read username from JWT:",
                error
            );
        }
    }, []);

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

            <div className="flex items-center gap-4">
                {username && (
                    <span className="text-sm text-gray-300">
                        Welcome,{" "}
                        <span className="font-semibold text-white">
                            {username}
                        </span>
                    </span>
                )}

                <button
                    onClick={handleLogout}
                    className="rounded-lg px-4 py-2 text-sm font-medium text-gray-300 transition hover:bg-gray-800 hover:text-white"
                >
                    Logout
                </button>
            </div>
        </header>
    );
}