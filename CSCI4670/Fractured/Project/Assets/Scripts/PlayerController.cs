using UnityEngine;

// Include the namespace required to use Unity UI
using UnityEngine.UI;

using System.Collections;

public class PlayerController : MonoBehaviour {
	
	// Create public variables for player speed, and for the Text UI game objects
	public float speed;

	public GameObject gameOverPanel;
	public GameObject gameActivePanel;

	public Text gameOverText;
	public Text scoreText;

	public GameObject camera;
	
	// Create private references to the rigidbody component on the player, and the count of pick up objects picked up so far
	private Rigidbody rb;
	private double score = 0;
	private double mult = 1.0;
	private int scoreSkew = 10;

	private Animator cameraAnimator;

	private bool isGrounded;
	private Vector3 jump;
	public float jumpForce = 2.0f;

	// At the start of the game..
	void Start ()
	{

		cameraAnimator = camera.GetComponent<Animator>();
		// Assign the Rigidbody component to our private rb variable
		rb = GetComponent<Rigidbody>();

		jump = new Vector3(0.0f, 2.0f, 0.0f);

		// Run the SetCountText function to update the UI (see below)
		SetCountText ();

		// Set the text property of our Win Text UI to an empty string, making the 'You Win' (game over message) blank
		gameOverText.text = "";
	}

	// Each physics step..
	void FixedUpdate ()
	{

		if(Input.GetKeyDown(KeyCode.Space) && isGrounded){
     
            rb.AddForce(jump * jumpForce, ForceMode.Impulse);
            isGrounded = false;
        }


		// Set some local float variables equal to the value of our Horizontal and Vertical Inputs
		float moveHorizontal = Input.GetAxis ("Horizontal");
		float moveVertical = Input.GetAxis ("Vertical");

		// Create a Vector3 variable, and assign X and Z to feature our horizontal and vertical float variables above
		Vector3 movement = new Vector3 (moveHorizontal, 0.0f, moveVertical);

		// Add a physical force to our Player rigidbody using our 'movement' Vector3 above, 
		// multiplying it by 'speed' - our public player speed that appears in the inspector
		rb.AddForce (movement * speed);
		rb.AddForce(Physics.gravity * rb.mass);//increases our gravity

		score += Time.deltaTime*mult*scoreSkew;
		SetCountText();
	}

	void OnCollisionStay(){

        isGrounded = true;
    }

	// When this game object intersects a collider with 'is trigger' checked, 
	// store a reference to that collider in a variable named 'other'..
	void OnTriggerEnter(Collider other) 
	{
		// ..and if the game object we intersect has the tag 'Pick Up' assigned to it..
		if (other.gameObject.CompareTag ("Coin"))
		{
			// Make the other game object (the pick up) inactive, to make it disappear
			Destroy(other.gameObject);

			int caseIndex = Random.Range(0,3);

			switch (caseIndex){
          		case 0:

             		cameraAnimator.SetTrigger("Twist");
              	break;

          		case 1:

              		cameraAnimator.SetTrigger("Fish");
              	break;

          		default:
              	
              		//donothing
              	break;
     		 }

			// Add one to the score variable 'count'
			mult = mult + 0.15;
		}

		if (other.gameObject.CompareTag ("Enemy Object")){

			Renderer rend = GetComponent<Renderer>();
			Collider coll = GetComponent<Collider>();
			Rigidbody rigi = GetComponent<Rigidbody>();



			rend.enabled = false;
			coll.enabled = false;
			rigi.isKinematic = true;
			
			gameActivePanel.SetActive(false);
			gameOverPanel.SetActive(true);

			cameraAnimator.SetTrigger("MusicOff");

			int scoreFloored = (int)score;

			gameOverText.text = "Game Over\nScore:\n" + scoreFloored.ToString();
	
		}
	}

	// Create a standalone function that can update the 'countText' UI and check if the required amount to win has been achieved
	void SetCountText()
	{
		// Update the text field of our 'countText' variable
		int scoreFloored = (int)score;
		scoreText.text = scoreFloored.ToString() + " " +mult.ToString() + "x";
	}
}