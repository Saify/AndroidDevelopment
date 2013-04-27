package ca.fusiondev.rockpaperscissor;

import java.io.InputStream;
import java.util.Random;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

;

public class Playground extends Activity implements OnClickListener {

	private Player player1;
	private Player player2;
	private ImageView ivWeaponP1, ivWeaponP2;
	private TextView nameP1, nameP2, tvScoreP1, tvScoreP2;
	private Button bRockP1, bPaperP1, bScissorP1, bRockP2, bPaperP2,
			bScissorP2, bFight;
	RelativeLayout rl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.title_activity);
		initialize();
		createPlayerWithWeapon();

	}

	private void initialize() {
		ivWeaponP1 = (ImageView) findViewById(R.id.ivWeaponPlayer1);
		ivWeaponP2 = (ImageView) findViewById(R.id.ivWeaponPlayer2);
		nameP1 = (TextView) findViewById(R.id.tvNameP1);
		nameP2 = (TextView) findViewById(R.id.tvNameP2);
		bPaperP1 = (Button) findViewById(R.id.bPaperP1);
		bRockP1 = (Button) findViewById(R.id.bRockP1);
		bScissorP1 = (Button) findViewById(R.id.bSicessorP1);
		bPaperP2 = (Button) findViewById(R.id.bPaperP2);
		bRockP2 = (Button) findViewById(R.id.bRockP2);
		bScissorP2 = (Button) findViewById(R.id.bSicessorP2);
		bFight = (Button) findViewById(R.id.bFight);
		tvScoreP1 = (TextView) findViewById(R.id.tvScoreP1);
		tvScoreP2 = (TextView) findViewById(R.id.tvScoreP2);
		bPaperP1.setOnClickListener(this);
		bRockP1.setOnClickListener(this);
		bScissorP1.setOnClickListener(this);
		bPaperP2.setOnClickListener(this);
		bRockP2.setOnClickListener(this);
		bScissorP2.setOnClickListener(this);
		bFight.setOnClickListener(this);
	}

	private void createPlayerWithWeapon() {
		player1 = new Player("Computer", 0);
		player2 = new Player("Saif", 0);
		nameP1.setText(player1.getName());
		nameP2.setText(player2.getName());

	}

	private void fight() {

		if (player2.getWeapon() == null) {
			Toast.makeText(Playground.this, "Choose a weapon first!",
					Toast.LENGTH_SHORT).show();
		} else {

			getOpponentWeapon();
			getWinner();
		}
	}

	private void getWinner() {

		if (player2.getSelectedWeapon() == Weapon.paper
				&& player1.getSelectedWeapon() == Weapon.rock) {
			setScore(player2, tvScoreP2);
			announceWinner(player2.getName());
		} else if (player2.getSelectedWeapon() == Weapon.rock
				&& player1.getSelectedWeapon() == Weapon.scissor) {
			setScore(player2, tvScoreP2);
			announceWinner(player2.getName());
		} else if (player2.getSelectedWeapon() == Weapon.scissor
				&& player1.getSelectedWeapon() == Weapon.paper) {
			setScore(player2, tvScoreP2);
			announceWinner(player2.getName());
		} else if (player2.getSelectedWeapon() == player1.getSelectedWeapon()) {
			Toast.makeText(Playground.this, "Fight Draw", Toast.LENGTH_SHORT)
					.show();
		} else {
			setScore(player1, tvScoreP1);
			announceWinner(player1.getName());
		}

	}

	private void setScore(Player player, TextView view) {
		player.setCurrentScore(player.getCurrentScore() + 1);
		view.setText(String.valueOf(player.getCurrentScore()));
	}

	private void announceWinner(String winnerName) {
		Toast.makeText(Playground.this, winnerName + " Won!",
				Toast.LENGTH_SHORT).show();
		rl.setVisibility(View.VISIBLE);
	}

	private void getOpponentWeapon() {
		final Random crazy = new Random();
		rl = (RelativeLayout) findViewById(R.id.rlPlayer2);
		switch (crazy.nextInt(4)) {
		case Weapon.paper:
			player1.setSelectedWeapon(Weapon.paper);
			setWeapon(R.raw.iv_paper_left, ivWeaponP1);
			break;
		case Weapon.rock:
			player1.setSelectedWeapon(Weapon.rock);
			setWeapon(R.raw.iv_rock_left, ivWeaponP1);
			break;
		case Weapon.scissor:
			player1.setSelectedWeapon(Weapon.scissor);
			setWeapon(R.raw.iv_scissor_left, ivWeaponP1);
			break;
		default:
		getOpponentWeapon();
		break;
		}
	}

	// private void getOpponentWeapon() {
	// final Random crazy = new Random();
	// final Handler handler = new Handler();
	// rl = (RelativeLayout) findViewById(R.id.rlPlayer2);
	// // rl.setVisibility(View.GONE);
	// Runnable runnable = new Runnable() {
	// int i = 0;
	// public void run() {
	// switch (crazy.nextInt(4)) {
	// case Weapon.paper:
	// player1.setSelectedWeapon(Weapon.paper);
	// setWeapon(R.raw.iv_paper_left, ivWeaponP1);
	// break;
	// case Weapon.rock:
	// player1.setSelectedWeapon(Weapon.rock);
	// setWeapon(R.raw.iv_rock_left, ivWeaponP1);
	// break;
	// case Weapon.scissor:
	// player1.setSelectedWeapon(Weapon.scissor);
	// setWeapon(R.raw.iv_scissor_left, ivWeaponP1);
	// break;
	// }
	// if (i < 15) {
	// handler.postDelayed(this, 200);
	// }else{
	// getWinner();
	// }
	// i++;
	// }
	// };
	// getWinner();
	// handler.postDelayed(runnable, 100);
	// }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.title, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bFight:
			fight();
			break;
		case R.id.bPaperP1:
			player1.setSelectedWeapon(Weapon.paper);
			setWeapon(R.raw.iv_paper_left, ivWeaponP1);
			break;
		case R.id.bRockP1:
			player1.setSelectedWeapon(Weapon.rock);
			setWeapon(R.raw.iv_rock_left, ivWeaponP1);
			break;
		case R.id.bSicessorP1:
			player1.setSelectedWeapon(Weapon.scissor);
			setWeapon(R.raw.iv_scissor_left, ivWeaponP1);
			break;
		case R.id.bPaperP2:
			player2.setSelectedWeapon(Weapon.paper);
			setWeapon(R.raw.iv_paper_right, ivWeaponP2);
			break;
		case R.id.bRockP2:
			player2.setSelectedWeapon(Weapon.rock);
			setWeapon(R.raw.iv_rock_right, ivWeaponP2);
			break;
		case R.id.bSicessorP2:
			player2.setSelectedWeapon(Weapon.scissor);
			setWeapon(R.raw.iv_scissor_right, ivWeaponP2);
			break;

		default:
			break;
		}
	}

	private void setWeapon(int imageID, ImageView ivWeapon) {
		InputStream imageStream = getResources().openRawResource(imageID);
		ivWeapon.setImageBitmap(BitmapFactory.decodeStream(imageStream));
	}
}
