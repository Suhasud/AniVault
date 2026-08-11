import api from "@/lib/api";

export interface Anime {
    id: number;
    title: string;
    genres: string[];
    studio: string;
    episodes: number;
    watchedEpisodes: number;
    animeStatus: "ONGOING" | "COMPLETED" | "UPCOMING";
    watchStatus: "PLANNING" | "WATCHING" | "COMPLETED" | "DROPPED";
    imageUrl: string | null;
}

export interface AnimePage {
    content: Anime[];
    totalElements: number;
    totalPages: number;
    number: number;
    size: number;
}

export interface AnimeFilters {
    title?: string;
    studio?: string;
    genre?: string;
    watchStatus?: string;
    animeStatus?: string;
    page?: number;
    size?: number;
    sort?: string;
}

export interface AnimeRequest {
    title: string;
    genres: string[];
    studio: string;
    episodes: number;
    watchedEpisodes: number;
    animeStatus: "ONGOING" | "COMPLETED" | "UPCOMING";
    watchStatus: "PLANNING" | "WATCHING" | "COMPLETED" | "DROPPED";
}

export const animeService = {
    async getAll(
        filters: AnimeFilters = {},
    ): Promise<AnimePage> {
        const response = await api.get<AnimePage>("/anime", {
            params: filters,
        });

        return response.data;
    },

    async getById(id: number): Promise<Anime> {
        const response = await api.get<Anime>(`/anime/${id}`);

        return response.data;
    },

    async create(anime: AnimeRequest): Promise<Anime> {
        const response = await api.post<Anime>("/anime", anime);

        return response.data;
    },

    async update(
        id: number,
        anime: AnimeRequest,
    ): Promise<Anime> {
        const response = await api.put<Anime>(
            `/anime/${id}`,
            anime,
        );

        return response.data;
    },
    async delete(id: number): Promise<void> {
        await api.delete(`/anime/${id}`);
    },
};