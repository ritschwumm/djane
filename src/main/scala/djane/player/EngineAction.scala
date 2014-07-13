package djane.player

/** changes to an Engine's state */
object EngineAction {
	case class ChangeControl(
		speaker:Double,
		phone:Double
	)
	extends EngineAction
	
	case class SetBeatRate(beatRate:Double) 						extends EngineAction
	
	case class ControlPlayer(player:Int, actions:Seq[PlayerAction])	extends EngineAction
}

sealed abstract class EngineAction
