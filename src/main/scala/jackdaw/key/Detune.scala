package jackdaw.key

object Detune {
	case object VeryLow		extends Detune
	case object Low			extends Detune
	case object InTune		extends Detune
	case object High		extends Detune
	case object VeryHigh	extends Detune
}

sealed trait Detune
