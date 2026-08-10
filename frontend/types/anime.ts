export type AnimeStatus = "ONGOING" | "COMPLETED" | "UPCOMING";

export type WatchStatus =
    | "PLANNING"
    | "WATCHING"
    | "COMPLETED"
    | "DROPPED";

export interface AnimeRequest {
    title: string;
    genres: string[];
    studio: string;
    episodes: number;
    watchedEpisodes: number;
    animeStatus: AnimeStatus;
    watchStatus: WatchStatus;
}

export interface AnimeResponse extends AnimeRequest {
    id: number;
}