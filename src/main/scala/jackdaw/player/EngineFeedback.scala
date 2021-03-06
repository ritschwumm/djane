package jackdaw.player

object EngineFeedback {
	val empty	= EngineFeedback(
		masterPeak	= 0,
		player1		= PlayerFeedback.empty,
		player2		= PlayerFeedback.empty,
		player3		= PlayerFeedback.empty
	)
}

final case class EngineFeedback(
	masterPeak:Float,
	player1:PlayerFeedback,
	player2:PlayerFeedback,
	player3:PlayerFeedback
)
