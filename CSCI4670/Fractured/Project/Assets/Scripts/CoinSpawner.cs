using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CoinSpawner : MonoBehaviour {

	public GameObject[] SpawnPoints;
	public GameObject Coin;
  	
	public float deltaT;
	private float nextSpawnTime;

  public int dificulty = 1;

  	/**
     * Use this for initialization
     */
    void Start (){

      nextSpawnTime = Time.time + deltaT;
    }



    /**
     * Update is called once per frame
     */
    void Update (){

        if (Time.time >= nextSpawnTime){

          SpawnMazeObject();
          nextSpawnTime = Time.time + deltaT;
        }
    }

    void SpawnMazeObject(){

      int spawnCount = Random.Range(1, dificulty);

      for( int i = 0 ; i < spawnCount; i++){

        Instantiate(Coin, SpawnPoints[Random.Range(0, SpawnPoints.Length)].transform);
      }
    }
}