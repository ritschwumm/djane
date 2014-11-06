name			:= "jackdaw"

organization	:= "de.djini"

version			:= "1.9.0"

scalaVersion	:= "2.11.4"

scalacOptions	++= Seq(
	"-deprecation",
	"-unchecked",
	"-language:implicitConversions",
	// "-language:existentials",
	// "-language:higherKinds",
	// "-language:reflectiveCalls",
	// "-language:dynamics",
	// "-language:postfixOps",
	// "-language:experimental.macros"
	"-feature",
	"-optimize"
)

conflictManager	:= ConflictManager.strict

libraryDependencies	++= Seq(
	"de.djini"					%%	"scutil-core"	% "0.55.0"	% "compile",
	"de.djini"					%%	"scutil-swing"	% "0.55.0"	% "compile",
	"de.djini"					%%	"scaudio"		% "0.40.0"	% "compile",
	"de.djini"					%%	"scjson"		% "0.60.0"	% "compile",
	"de.djini"					%%	"screact"		% "0.61.0"	% "compile",
	"de.djini"					%%	"scgeom"		% "0.25.0"	% "compile",
	"de.djini"					%%	"sc2d"			% "0.19.0"	% "compile",
	"org.simplericity.macify"	%	"macify"		% "1.6"		% "compile",
	"javazoom"					%	"jlayer"		% "1.0.1"	% "compile",
	"com.mpatric"				%	"mp3agic"		% "0.8.2"	% "compile",
	"de.jarnbjo"				%	"j-ogg-all"		% "1.0.0"	% "compile"
)

enablePlugins(ScriptStartPlugin, OsxAppPlugin, CapsulePlugin)

//------------------------------------------------------------------------------

buildInfoSettings

sourceGenerators in Compile	<+= buildInfo

buildInfoKeys		:= Seq[BuildInfoKey](name, version)

buildInfoPackage	:= "jackdaw"

//--------------------------------------------------------------------------------

scriptstartConfigs	:= Seq(ScriptConfig(
	scriptName	= "jackdaw",
	mainClass	= "jackdaw.Boot",
	vmOptions	= Seq(
		"-server",  
		"-Xms48m",
		"-Xmx128m",
		"-Xincgc"
		// "-XX:+UnlockExperimentalVMOptions",
		// "-XX:+UseG1GC"
		// "-XX:+PrintGCDetails",
	),
	systemProperties	= Map(
		// java.lang.IllegalArgumentException: Comparison method violates its general contract!
		// 	at java.util.TimSort.mergeHi(TimSort.java:868)
		//	...
		//	at sun.awt.datatransfer.DataTransferer.setToSortedDataFlavorArray(DataTransferer.java:2407)
		// @see http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=7193557
		// @see http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=7173464
		"java.util.Arrays.useLegacyMergeSort"	-> "true",
		//	prevents memleaks for windows
		"swing.bufferPerWindow"					-> "false"
		//
		// @see http://download.oracle.com/javase/1.5.0/docs/guide/2d/flags.html
		// "sun.java2d.trace"	-> "log"
		//
		//	new xrender pipeline
		//	"-Dsun.java2d.xrender=True"
		//
		// crashes on too many systems, fbobject=false helps
		//	"-Dsun.java2d.opengl=True",
		//	"-Dsun.java2d.opengl.fbobject=false"
		//
		// allows jvisualvm
		//	"-Dcom.sun.management.jmxremote.local.only=false"
	)
))

//------------------------------------------------------------------------------

osxappBundleName	:= "jackdaw"

osxappBundleIcons	:= baseDirectory.value / "src/main/osxapp/default.icns"

osxappVm			:= OracleJava7()

osxappMainClass		:= Some("jackdaw.Boot")

osxappVmOptions		:= Seq(
	"-server",
	"-Xms48m",
	"-Xmx128m",
	"-Xincgc"
)

osxappSystemProperties	:= Map(
	// fix dnd exception
	"java.util.Arrays.useLegacyMergeSort"	-> "true",
	//	prevents memleaks for windows
	"swing.bufferPerWindow"					-> "false"
)

//------------------------------------------------------------------------------

capsuleMainClass			:= Some("jackdaw.Boot")

capsuleVmOptions			:= Seq(
	"-server",
	"-Xms48m",
	"-Xmx128m",
	"-Xincgc"
)

capsuleSystemProperties		:= Map(
	// fix dnd exception
	"java.util.Arrays.useLegacyMergeSort"	-> "true",
	//	prevents memleaks for windows
	"swing.bufferPerWindow"					-> "false"
)

capsuleMinJavaVersion		:= Some("1.7.0")

capsuleMakeExecutable		:= true

//------------------------------------------------------------------------------

TaskKey[Seq[File]]("bundle")	:= Seq(
	scriptstartZip.value,
	osxappZip.value,
	capsule.value
)
