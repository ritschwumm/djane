package jackdaw.data

object RhythmValue {
	def zero(unit:RhythmUnit):RhythmValue	= RhythmValue(0, unit)
	def one(unit:RhythmUnit):RhythmValue	= RhythmValue(1, unit)
}

final case class RhythmValue(steps:Double, unit:RhythmUnit) {
	def move(steps:Double):RhythmValue		= RhythmValue(this.steps + steps, unit)
	def scale(factor:Double):RhythmValue	= RhythmValue(this.steps * factor, unit)
}
