using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CreditController : MonoBehaviour {

	public float SpeedMultiplier = 100;

	// Use this for initialization
	void Start () {

	}
	
	// Update is called once per frame
	void Update () {

		transform.position += transform.forward * Time.deltaTime * SpeedMultiplier;

		if(transform.position.z <= -10){

			Destroy(gameObject);
		}
		
	}
}
