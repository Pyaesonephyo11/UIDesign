package com.uitest.uidesign.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.uitest.uidesign.Constants
import com.uitest.uidesign.R
import com.uitest.uidesign.ui.theme.BlackTransparent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailScreen(navController: NavController) {

  Column(
    modifier = Modifier
      .clip(
        shape = RoundedCornerShape(
          topStartPercent = 10,
          topEndPercent = 10
        )
      )
      .verticalScroll(state = rememberScrollState())
  ) {

    Box(modifier = Modifier.height(250.dp)) {

      val pagerState = rememberPagerState(pageCount = {
        3
      }
      )

      HorizontalPager(
        state = pagerState,
        modifier = Modifier
          .fillMaxWidth()
      ) {
        Image(
          painter = painterResource(id = R.drawable.img_alligator_gar),
          contentDescription = null,
          modifier = Modifier.fillMaxWidth(),
          contentScale = ContentScale.Crop
        )
      }

      Column(
        modifier = Modifier
          .matchParentSize()
          .background(color = BlackTransparent)
      ) {

        Image(
          painter = painterResource(id = R.drawable.ic_close),
          contentDescription = null,
          modifier = Modifier
            .padding(16.dp)
            .size(34.dp)
            .clickable {
              navController.navigateUp()
            }
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(
          modifier = Modifier
            .wrapContentHeight()
            .padding(start = 20.dp, bottom = 20.dp),
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

    }

    //content
    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {

      Text(text = "ZONE 1", fontSize = 14.sp, color = MaterialTheme.colorScheme.outline)

      Text(
        text = "Alligator Gar",
        fontSize = 28.sp,
        color = MaterialTheme.colorScheme.onSurface,
        fontWeight = FontWeight.Bold
      )

      Row(
        modifier = Modifier
          .border(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant,
            shape = RoundedCornerShape(6.dp)
          )
          .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {

        Image(
          modifier = Modifier.size(16.dp),
          painter = painterResource(id = R.drawable.ic_walk),
          contentDescription = null
        )

        Text(text = "410m away", fontSize = 14.sp, color = MaterialTheme.colorScheme.outline)

        Text(text = "Map", fontSize = 14.sp, color = Color.Red)

      }

      Text(
        text = Constants.dummyText,
        fontSize = 15.sp,
        color = MaterialTheme.colorScheme.onSurfaceVariant
      )

    }
  }

}