package com.projekt.backend.ServiceTests;

import com.projekt.backend.Entity.Playlist;
import com.projekt.backend.Entity.Track;
import com.projekt.backend.Repository.PlaylistRepository;
import com.projekt.backend.Repository.TrackRepository;
import com.projekt.backend.Repository.UserRepository;
import com.projekt.backend.Service.PlaylistService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class PlaylistServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PlaylistRepository playlistRepository;
    @Mock
    private TrackRepository trackRepository;
    private AutoCloseable openMocks;
    private PlaylistService playlistService;

    @BeforeEach
    public void init(){
        openMocks = MockitoAnnotations.openMocks(this);
        playlistService = new PlaylistService(playlistRepository,userRepository,trackRepository);
    }

    @AfterEach
    public void tearDown() throws Exception{
        openMocks.close();
    }

    @Test
    public void testGetUsersPlaylistsWhenPlaylistsExist() {
    List<Playlist> playlists = new ArrayList<>();
    playlists.add(new Playlist());

    when(playlistRepository.findByUser_Id(anyLong())).thenReturn(playlists);

    Iterable<Playlist> result = playlistService.getUsersPlaylists(1L);

    assertNotNull(result);
    assertTrue(result.iterator().hasNext());
    }

    @Test
    public void testGetUsersPlaylistsWhenNoPlaylistsExist() {
        when(playlistRepository.findByUser_Id(anyLong())).thenReturn(new ArrayList<>());
        Iterable<Playlist> result = playlistService.getUsersPlaylists(1L);
        assertNull(result);
    }

    @Test
    public void testGetPlaylistWhenPlaylistExists() {
        Playlist playlist = new Playlist();
        playlist.setDatabaseId(1L);
        when(playlistRepository.findById(anyLong())).thenReturn(Optional.of(playlist));
        Playlist result = playlistService.getPlaylist(1L);
        assertNotNull(result);
        assertEquals(1L, result.getDatabaseId());
    }

    @Test
    public void testGetPlaylistWhenPlaylistDoesNotExist() {
        when(playlistRepository.findById(anyLong())).thenReturn(Optional.empty());
        Playlist result = playlistService.getPlaylist(1L);
        assertNull(result);
    }

    @Test
    public void testRemoveTrackFromPlaylistWhenPlaylistExists() {
        Playlist playlist = new Playlist();
        playlist.setDatabaseId(1L);

        Track trackToRemove = new Track();
        trackToRemove.setId(2L);
        trackToRemove.setUri("uri");

        Set<Track> tracks = new HashSet<>();
        tracks.add(new Track()); // Some other track
        tracks.add(trackToRemove); // Track to remove

        playlist.setPlaylistTracks(tracks);
        when(playlistRepository.findById(1L)).thenReturn(Optional.of(playlist));
        playlistService.removeTrackFromPlaylist(1L, 2L, "uri", 1L);

        assertEquals(1, playlist.getPlaylistTracks().size());
        assertFalse(playlist.getPlaylistTracks().contains(trackToRemove));
    }

    @Test
    public void testRemoveTrackFromPlaylistWhenPlaylistDoesNotExist() {
        when(playlistRepository.findById(1L)).thenReturn(Optional.empty());
        playlistService.removeTrackFromPlaylist(1L, 2L, "uri", 1L);
        verify(playlistRepository, never()).save(any());
    }


    @Test
    public void testAddTrackToPlaylistWhenPlaylistAndTrackExist() {
        Playlist playlist = new Playlist();
        playlist.setDatabaseId(1L);

        Track trackToAdd = new Track();
        trackToAdd.setId(2L);
        trackToAdd.setUri("uri");
        when(playlistRepository.findById(1L)).thenReturn(Optional.of(playlist));
        when(trackRepository.findById(2L)).thenReturn(Optional.of(trackToAdd));
        playlistService.addTrackToPlaylist(1L, 2L, "uri", 1L);
        assertTrue(playlist.getPlaylistTracks().contains(trackToAdd));
    }

    @Test
    public void testAddTrackToPlaylistWhenPlaylistDoesNotExist() {
        when(playlistRepository.findById(1L)).thenReturn(Optional.empty());
        playlistService.addTrackToPlaylist(1L, 2L, "track_uri_2", 1L);
        verify(playlistRepository, never()).save(any());
    }

    @Test
    public void testAddTrackToPlaylistWhenTrackDoesNotExist() {
        when(playlistRepository.findById(1L)).thenReturn(Optional.of(new Playlist()));
        when(trackRepository.findById(2L)).thenReturn(Optional.empty());
        playlistService.addTrackToPlaylist(1L, 2L, "track_uri_2", 1L);
        verify(playlistRepository, never()).save(any());
    }

    @Test
    public void testEditPlaylistDetailsWhenPlaylistExists() {
        Playlist playlist = new Playlist();
        playlist.setDatabaseId(1L);
        playlist.setName("Old Name");
        playlist.setDescription("Old Description");
        playlist.setPublic(false);


        Playlist updatedPlaylist = new Playlist();
        updatedPlaylist.setDatabaseId(1L);
        updatedPlaylist.setName("New Name");
        updatedPlaylist.setDescription("New Description");
        updatedPlaylist.setPublic(true);

        when(playlistRepository.findById(1L)).thenReturn(Optional.of(playlist));
        playlistService.editPlaylistDetails(updatedPlaylist, 1L);

        assertEquals("New Name", playlistRepository.findById(1L).get().getName());
        assertEquals("New Description", playlistRepository.findById(1L).get().getDescription());
        assertTrue(playlist.getPublic());
    }

    @Test
    public void testEditPlaylistDetailsWhenPlaylistDoesNotExist() {
        when(playlistRepository.findById(1L)).thenReturn(Optional.empty());
        Playlist updatedPlaylist = new Playlist();
        updatedPlaylist.setDatabaseId(1L);
        playlistService.editPlaylistDetails(updatedPlaylist, 1L);
        verify(playlistRepository, never()).save(any());
    }


}
