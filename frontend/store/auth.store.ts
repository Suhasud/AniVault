import { create } from "zustand";

interface AuthState {
    token: string | null;
    setToken: (token: string) => void;
    clearToken: () => void;
}

const getStoredToken = (): string | null => {
    if (typeof window === "undefined") {
        return null;
    }

    return localStorage.getItem("anivault_token");
};

export const useAuthStore = create<AuthState>((set) => ({
    token: getStoredToken(),

    setToken: (token) => {
        localStorage.setItem("anivault_token", token);
        set({ token });
    },

    clearToken: () => {
        localStorage.removeItem("anivault_token");
        set({ token: null });
    },
}));