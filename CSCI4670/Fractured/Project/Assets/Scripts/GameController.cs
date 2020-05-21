using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GameController : MonoBehaviour {

	public GameObject[] SpawnPoints;
	public GameObject[] MazeObjects;
  public GameObject Coin;
  	
	public float deltaT = 1;
	private float nextSpawnTime;

  public int dificulty = 1;
  public int coinSpawnChance = 4;
  
  void Start (){

    nextSpawnTime = Time.time + deltaT;
  }


  void Update (){

      if (Time.time >= nextSpawnTime){

        SpawnMazeObject();
        nextSpawnTime = Time.time + deltaT;
      }
  }

  void SpawnMazeObject(){


    for( int i = 0 ; i < SpawnPoints.Length; i++){

      int determineSpawn = Random.Range(1,11);
      int mazeObjectIndex = Random.Range(0, MazeObjects.Length);

      if(determineSpawn <= (.1*dificulty + 1) ){

        Instantiate(MazeObjects[mazeObjectIndex], SpawnPoints[i].transform);
      }
      else{

        if(Random.Range(1, coinSpawnChance + 1) == coinSpawnChance){

        Instantiate(Coin, SpawnPoints[i].transform);
        }
      }
    }
  }
}