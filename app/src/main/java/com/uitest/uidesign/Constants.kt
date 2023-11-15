package com.uitest.uidesign

data class MainMenuViewItem(val iconId: Int, val label: String)

data class ShowViewItem(val imgId: Int, val title: String, val time: String)

object Constants {
  val mainMenu = listOf(
    MainMenuViewItem(iconId = R.drawable.ic_map, label = "Map"),
    MainMenuViewItem(iconId = R.drawable.ic_inhabitant, label = "Inhabitants"),
    MainMenuViewItem(iconId = R.drawable.ic_show, label = "Shows"),
    MainMenuViewItem(iconId = R.drawable.ic_shopping, label = "Shopping"),
    MainMenuViewItem(iconId = R.drawable.ic_dine, label = "Dine"),
    MainMenuViewItem(iconId = R.drawable.ic_meet_greet, label = "Meet & Greets")
  )

  val shows = listOf(
    ShowViewItem(
      imgId = R.drawable.img_card1,
      title = "Dive Feeding @ Shipwreck",
      time = "2:30 PM"
    ),
    ShowViewItem(
      imgId = R.drawable.img_card2,
      title = "Say cheese to Shark",
      time = "2:40 PM"
    ),
    ShowViewItem(
      imgId = R.drawable.img_card1,
      title = "Dive Feeding @ Shipwreck",
      time = "2:50 PM"
    )
  )


  val dummyText =
    "Blandittempor ignota persequeris ridiculus dissentiunt tibique sagittis mi alterum iudicabit gravida noster ei referrentur vituperatoribus fringilla nibh maecenas.  Hisintellegebat laoreet comprehensam harum etiam habeo eu inani definitiones oratio.  Viriseuripidis eget tellus netus meliore venenatis errem." +
        "\n\nScriptapersius debet labores elit delenit potenti pulvinar delectus sit ac moderatius.  Sagittiscurae tempus maluisset maecenas ponderum maecenas consectetuer vocent parturient potenti dicta delectus nullam suscipit.  Splendideaudire ancillae sumo impetus eripuit volutpat scripserit theophrastus qualisque his velit habitant.  Arcumelius deseruisse mnesarchum constituam platonem graeci vocent ridiculus.  Alienumhis deserunt fugit nisl. \n Verearparturient montes definitiones suscipiantur eum sem percipit cubilia mei facilisis vituperatoribus.  Dolortempor adversarium quem aeque himenaeos definitionem persecuti expetendis sale causae accusata ullamcorper civibus phasellus curae usu.  Erremalterum id ornare evertitur corrumpit dolorum repudiandae habitant laoreet his minim noster alterum fugit harum homero partiendo euripidis.  Eleifendeam fringilla vim euripidis."
}


