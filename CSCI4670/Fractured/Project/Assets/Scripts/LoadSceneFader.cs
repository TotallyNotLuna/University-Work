using System.Collections;
using System.Collections.Generic;
using UnityEngine.SceneManagement;
using UnityEngine;

public class LoadSceneFader : MonoBehaviour {

	private int sceneToLoad;

	public void LoadByIndex(int sceneIndex){

		sceneToLoad = sceneIndex;

		GetComponent<Animator> ().SetTrigger("fadeOut");
	}

	public void OnFadeComplete(){

		SceneManager.LoadScene(sceneToLoad);
	}
}
