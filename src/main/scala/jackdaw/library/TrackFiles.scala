package jackdaw.library

import java.io.File

import scutil.implicits._

import jackdaw.migration.Migration

case class TrackFiles(meta:File) {
	val wav:File	= meta / "sample.wav"
	val curve:File	= meta / "bandCurve.bin"
	val data:File	= dataByVersion(Migration.latestVersion)
	
	def dataByVersion(version:TrackVersion):File	=
			version match {
				case TrackVersion(0)	=> meta / "data.json"
				case TrackVersion(x)	=> meta / so"data-v${x.toString}.json"
			}
}
