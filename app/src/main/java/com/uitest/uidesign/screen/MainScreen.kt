package com.uitest.uidesign.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.uitest.uidesign.Constants
import com.uitest.uidesign.R
import com.uitest.uidesign.ShowViewItem
import com.uitest.uidesign.UIDesignScreen
import com.uitest.uidesign.ui.theme.BlackTransparent
import com.uitest.uidesign.ui.theme.IconBackgroundColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MainScreen(navController: NavController) {
  Scaffold(topBar = {
    Row(
      modifier = Modifier.padding(vertical = 16.dp, horizontal = 26.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Icon(
        imageVector = Icons.Filled.KeyboardArrowLeft,
        contentDescription = "Back Arrow",
        tint = Color.Gray
      )

      Image(
        painter = painterResource(id = R.drawable.ic_toolbar_logo),
        contentDescription = null,
        modifier = Modifier
          .weight(1f)
          .height(50.dp)
      )

      Image(
        painter = painterResource(id = R.drawable.ic_notification),
        contentDescription = null,
        modifier = Modifier.size(24.dp)
      )

    }
  }, bottomBar = {
    BottomAppBar(containerColor = Color.White) {
      Row(
        modifier = Modifier
          .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
      ) {

        BottomBarItem(iconId = R.drawable.ic_nav_public, label = "Home", selected = true)

        BottomBarItem(iconId = R.drawable.ic_nav_wallet, label = "Wallet", iconSize = 28.dp)

        BottomBarItem(iconId = R.drawable.ic_nav_more, label = "More")
      }
    }
  }) { innerPadding ->
    MainContent(navController = navController, modifier = Modifier.padding(innerPadding))

  }
}

@Composable
fun BottomBarItem(
  modifier: Modifier = Modifier,
  @DrawableRes iconId: Int,
  label: String,
  iconSize: Dp = 32.dp,
  selected: Boolean = false
) {
  Column(
    modifier = modifier
      .padding(vertical = 4.dp, horizontal = 8.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Image(
      modifier = Modifier.size(iconSize),
      painter = painterResource(id = iconId),
      contentDescription = null
    )

    Spacer(modifier = Modifier.height(5.dp))

    Text(
      textAlign = TextAlign.Center,
      text = label,
      color = if (selected) Color.Black else Color.Gray,
      fontSize = 12.sp
    )
  }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainContent(navController: NavController, modifier: Modifier = Modifier) {

  Column(
    modifier = modifier
      .fillMaxSize()
      .verticalScroll(state = rememberScrollState()),
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {

    Box(contentAlignment = Alignment.BottomCenter) {
      val pagerState = rememberPagerState(pageCount = {
        3
      }
      )
      HorizontalPager(
        state = pagerState,
        modifier = Modifier
          .fillMaxWidth()
          .height(200.dp)
      ) {
        Image(
          painter = painterResource(id = R.drawable.img_daily_dive_feeding),
          contentDescription = null,
          modifier = Modifier.fillMaxSize(),
          contentScale = ContentScale.Crop
        )
      }

      Row(
        modifier = Modifier
          .wrapContentHeight()
          .fillMaxWidth()
          .padding(bottom = 20.dp),
        horizontalArrangement = Arrangement.Center
      ) {
        repeat(pagerState.pageCount) { iteration ->

          if (pagerState.currentPage == iteration) {
            Box(
              modifier = Modifier
                .padding(2.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(MaterialTheme.colorScheme.error)
                .height(5.dp)
                .width(50.dp)
            )
          } else {
            Box(
              modifier = Modifier
                .padding(2.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .height(5.dp)
                .width(50.dp)
            )
          }

        }
      }
      var key by remember {
        mutableStateOf(false)
      }

      LaunchedEffect(key) {
        launch {
          delay(2000)
          with(pagerState) {
            val target = if (currentPage < pageCount - 1) currentPage + 1 else 0
            animateScrollToPage(page = target)
            key = !key
          }
        }

      }

      /*   Text(
           text = "Don\'t miss our\ndaily Dive Feeding!",
           color = Color.White,
           fontSize = 30.sp,
           modifier = Modifier.padding(16.dp),
           maxLines = 2,
           style = LocalTextStyle.current.copy(
             lineHeight = 30.sp, shadow = Shadow(
               color = Color.Gray,
               offset = Offset(2.0f, 5.0f),
               blurRadius = 5f
             )
           ),
         )*/
    }

    LazyVerticalGrid(
      modifier = Modifier
        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
        .heightIn(max = 2000.dp),
      columns = GridCells.Fixed(count = 4),
      horizontalArrangement = Arrangement.spacedBy(16.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      items(Constants.mainMenu) { item ->
        MainMenuItem(modifier = Modifier.clickable {
          navController.navigate(UIDesignScreen.Detail.name)
        }, iconId = item.iconId, label = item.label)
      }

    }

    Row(
      modifier = Modifier
        .padding(top = 16.dp, start = 16.dp, end = 16.dp),
      horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
      ElevatedCard(
        elevation = CardDefaults.cardElevation(
          defaultElevation = 3.dp
        ),
        colors = CardDefaults.cardColors(
          containerColor = Color.White,
        ),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
          .wrapContentHeight()
          .weight(1f)

      ) {

        Column(
          modifier = Modifier.padding(12.dp),
          verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "My e-tickets", fontSize = 14.sp)
            Spacer(modifier = Modifier.weight(1f))
            Image(
              painter = painterResource(id = R.drawable.ic_e_ticket),
              contentDescription = null,
              modifier = Modifier.size(28.dp)
            )
          }

          Text(
            text = "There aren\'t any yet.",
            color = MaterialTheme.colorScheme.outlineVariant,
            fontSize = 18.sp
          )

          Text(text = "Retrieve here", fontSize = 14.sp, color = Color.Red)
        }

      }

      ElevatedCard(
        elevation = CardDefaults.cardElevation(
          defaultElevation = 3.dp
        ),
        colors = CardDefaults.cardColors(
          containerColor = Color.White,
        ),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
          .wrapContentHeight()
          .weight(1f)

      ) {

        Column(
          modifier = Modifier.padding(12.dp),
          verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Park hours", fontSize = 14.sp)
            Spacer(modifier = Modifier.weight(1f))
            Image(
              painter = painterResource(id = R.drawable.ic_park_hour),
              contentDescription = null,
              modifier = Modifier.size(28.dp)
            )
          }

          Text(
            text = "Today, 13 Feb 10am-5pm",
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
          )

          Text(text = "Plan my visit", fontSize = 14.sp, color = Color.Red)
        }

      }
    }

    Column(
      modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

      Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
          text = "Upcoming Shows",
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          fontSize = 18.sp
        )
        Spacer(modifier = Modifier.weight(1f))

        Text(text = "View all", fontSize = 14.sp, color = Color.Red)
      }

      LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        items(Constants.shows) { item ->
          ShowItem(item = item, modifier = Modifier.clickable {
            navController.navigate(UIDesignScreen.Detail.name)
          })
        }
      }

      Spacer(modifier = Modifier.height(16.dp))
    }
  }
}

@Composable
fun MainMenuItem(modifier: Modifier = Modifier, @DrawableRes iconId: Int, label: String) {
  Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {

    Box(
      modifier = Modifier
        .background(color = IconBackgroundColor, shape = RoundedCornerShape(100.dp))
        .padding(20.dp)
    ) {
      Image(
        painter = painterResource(id = iconId),
        contentDescription = null,
        modifier = Modifier
          .size(30.dp)
      )
    }

    Text(
      text = label,
      color = MaterialTheme.colorScheme.onSurface,
      fontSize = 14.sp,
      textAlign = TextAlign.Center
    )
  }
}

@Composable
fun ShowItem(modifier: Modifier = Modifier, item: ShowViewItem) {
  ElevatedCard(
    modifier = modifier.height(180.dp),
    elevation = CardDefaults.cardElevation(
      defaultElevation = 3.dp
    ),
    colors = CardDefaults.cardColors(
      containerColor = Color.White,
    ),
    shape = RoundedCornerShape(10.dp)
  ) {
    Box {
      Image(
        modifier = Modifier
          .fillMaxHeight(),
        painter = painterResource(id = item.imgId),
        contentDescription = null,
        contentScale = ContentScale.Crop
      )

      Column(
        modifier = Modifier
          .matchParentSize()
          .background(color = BlackTransparent)
          .padding(8.dp)
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(4.dp),
          modifier = Modifier
            .background(color = Color.White, shape = RoundedCornerShape(8.dp))
            .padding(5.dp)
        ) {
          Icon(
            painter = painterResource(id = R.drawable.baseline_access_time_24),
            contentDescription = null, tint = Color.Gray,
            modifier = Modifier.size(20.dp)
          )

          Text(text = item.time, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(text = item.title, color = MaterialTheme.colorScheme.surface, fontSize = 16.sp)
      }

    }

  }
}