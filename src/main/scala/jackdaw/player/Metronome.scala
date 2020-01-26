package jackdaw.player

import jackdaw.data._

trait MetronomeContext {
	def beatRateChanged(beatRate:Double):Unit
	def running:Boolean
}

object Metronome {
	val beatsPerMeasure	= Schema.default.beatsPerMeasure
}

/**
something a Player can be synced to
methods must never be called outside the engine thread
*/
final class Metronome(outputRate:Double, ctx:MetronomeContext) {
	private var bRate		= 1.0
	private var mIncrement	= 0.0
	private var mPhase		= 0.0
	private def bPhase		= mPhase * Metronome.beatsPerMeasure % 1.0

	private[player] def setBeatRate(beatRate:Double):Unit	= {
		bRate		= beatRate
		val mRate	= bRate / Metronome.beatsPerMeasure
		mIncrement	= mRate / outputRate

		ctx beatRateChanged beatRate
	}

	private[player] def step():Unit	= {
		if (ctx.running)	mPhase	= (mPhase + mIncrement) % 1.0
		else				mPhase	= 0
	}

	//------------------------------------------------------------------------------

	private[player] def phase(rhythmUnit:RhythmUnit):Double	=
		rhythmUnit match {
			// metronome phrases are exactly one measure
			case RhythmUnit.Phrase	=> mPhase
			case RhythmUnit.Measure	=> mPhase
			case RhythmUnit.Beat	=> bPhase
		}

	private[player] def	beatRate:Double	= bRate
}
