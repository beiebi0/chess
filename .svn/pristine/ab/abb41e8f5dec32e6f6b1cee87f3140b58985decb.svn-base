package org.longjuntan.hw3;

import com.google.gwt.dom.client.AudioElement;
import com.google.gwt.dom.client.MediaElement;
import com.google.gwt.media.client.Audio;

public class GameAudioFactory {
	public static Audio createAudio(String name) {
		Audio audio = Audio.createIfSupported();
		if (audio == null) {
			return null;
		}
		if (MediaElement.CAN_PLAY_PROBABLY.equals(audio
				.canPlayType(AudioElement.TYPE_OGG))) {
			audio.setSrc("audio_tlj/"+name+".ogg");
		} else if (MediaElement.CAN_PLAY_PROBABLY.equals(audio
				.canPlayType(AudioElement.TYPE_MP3))) {
			audio.setSrc("audio_tlj/"+name+".mp3");
		} else if (MediaElement.CAN_PLAY_MAYBE.equals(audio
				.canPlayType(AudioElement.TYPE_OGG))) {
			audio.setSrc("audio_tlj/"+name+".ogg");
		} else if (MediaElement.CAN_PLAY_MAYBE.equals(audio
				.canPlayType(AudioElement.TYPE_MP3))) {
			audio.setSrc("audio_tlj/"+name+".mp3");
		}
		return audio;
	}
}
