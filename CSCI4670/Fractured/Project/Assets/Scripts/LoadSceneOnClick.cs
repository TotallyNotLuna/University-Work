using System.Collections;
using System.Collections.Generic;
using UnityEngine.SceneManagement;
using UnityEngine;

public class LoadSceneOnClick : MonoBehaviour {

	public LoadSceneFader fader;

	public void LoadNewScene(int sceneIndex){

		fader.LoadByIndex(sceneIndex);
	}
}