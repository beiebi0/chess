package org.markanderson.hw5;

import java.util.ArrayList;

import com.google.gwt.dom.client.AudioElement;
import com.google.gwt.dom.client.MediaElement;
import com.google.gwt.media.client.Audio;

public class AudioHandler {

	public ArrayList<Audio> audioArray;

	public AudioHandler() {
		this.audioArray = new ArrayList<Audio>();
	}

	public void createAudioWithID(String id) {
		Audio audio = Audio.createIfSupported();

		if (audio == null) {
			return;
		}

		if (MediaElement.CAN_PLAY_PROBABLY.equals(audio
				.canPlayType(AudioElement.TYPE_OGG))) {
			audio.setSrc("markanderson_audio/" + id + ".ogg");

			audio.setVolume(1.0f);
			audio.setPreload(MediaElement.PRELOAD_AUTO);
		} else if (MediaElement.CAN_PLAY_PROBABLY.equals(audio
				.canPlayType(AudioElement.TYPE_MP3))) {
			audio.setSrc("markanderson_audio/" + id + ".mp3");

			audio.setVolume(1.0f);
			audio.setPreload(MediaElement.PRELOAD_AUTO);
		} else if (MediaElement.CAN_PLAY_PROBABLY.equals(audio
				.canPlayType(AudioElement.TYPE_WAV))) {
			audio.setSrc("markanderson_audio/" + id + ".wav");

			audio.setVolume(1.0f);
			audio.setPreload(MediaElement.PRELOAD_AUTO);
		} else if (MediaElement.CAN_PLAY_MAYBE.equals(audio
				.canPlayType(AudioElement.TYPE_WAV))) {
			audio.setSrc("markanderson_audio/" + id + ".wav");

			audio.setVolume(1.0f);
			audio.setPreload(MediaElement.PRELOAD_AUTO);
		} else if (MediaElement.CAN_PLAY_MAYBE.equals(audio
				.canPlayType(AudioElement.TYPE_OGG))) {
			audio.setSrc("markanderson_audio/" + id + ".ogg");

			audio.setVolume(1.0f);
			audio.setPreload(MediaElement.PRELOAD_AUTO);
		} else if (MediaElement.CAN_PLAY_MAYBE.equals(audio
				.canPlayType(AudioElement.TYPE_MP3))) {
			audio.setSrc("markanderson_audio/" + id + ".mp3");

			audio.setVolume(1.0f);
			audio.setPreload(MediaElement.PRELOAD_AUTO);
		}
		this.audioArray.add(audio);
	}
}
