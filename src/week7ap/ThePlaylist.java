package week7ap;

import java.util.Arrays;

/**
 * Problem 2: The Playlist
 * 
 * Scenario:
 * A music app lets you build a playlist of songs.
 * 
 * Problem Statement:
 * Design a Playlist class that stores songs internally but returns a safe copy of the list,
 * so nobody can sneak in changes from outside.
 * 
 * Requirements:
 * - Store song titles in a private array (assume a fixed maximum size).
 * - Provide a method to add a song, and one that returns all the songs added so far — as a copy, not the original array.
 * - Changing the array returned by that method must not affect the playlist's real contents.
 * - Provide a read-only count of how many songs are in the playlist.
 */
class Playlist {
    private final String[] songs;
    private int songCount;

    public Playlist(int capacity) {
        if (capacity <= 0) {
            capacity = 10;
        }
        this.songs = new String[capacity];
        this.songCount = 0;
    }

    public void addSong(String song) {
        if (song != null && songCount < songs.length) {
            songs[songCount++] = song;
        }
    }

    public String[] getSongs() {
        return Arrays.copyOf(songs, songCount);
    }

    public int getSongCount() {
        return songCount;
    }
}

public class ThePlaylist {
    public static void main(String[] args) {
        Playlist p = new Playlist(10);
        p.addSong("Song A");
        p.addSong("Song B");

        String[] copy = p.getSongs();
        System.out.println("Initial playlist copy: " + Arrays.toString(copy));

        copy[0] = "Hacked"; // Attempt to mutate external copy
        System.out.println("copy[0] = \"Hacked\"; // p.getSongs()[0] is still \"" + p.getSongs()[0] + "\"");
        System.out.println("p.getSongCount() = " + p.getSongCount());
    }
}
