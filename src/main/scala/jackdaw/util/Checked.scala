package jackdaw.util

import scutil.base.implicits._
import scutil.lang._

object Checked {
	def trueWin1(cond:Boolean, problem:String):Checked[Unit]	=
			cond trueWin problem1(problem)
	
	def fail1[T](problem:String):Checked[T]	=
			Fail(problem1(problem))
		
	def problem1(problem:String):Nes[String]	=
			Nes single problem
}
