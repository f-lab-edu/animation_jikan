package com.artem.animationjikan.presentation.ui.screen.detail.animation.tabs.review

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.artem.animationjikan.R


fun LazyListScope.reviewTab() {
    items(count = 15, key = { index -> "review_$index" }) {
        ReviewItem()
    }
}

@Composable
fun ReviewItem() {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://cdn.myanimelist.net/s/common/uploaded_files/1759433847-4e88065e62cc655729bc4a6fcf70bc24.jpeg?s=9aa7be20e5f99b4b4fcd721ec30d2499")
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(color = Color.LightGray),
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                "chekkit",
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.weight(1f))

            RatingBar(3)

        }

        Spacer(modifier = Modifier.height(20.dp))

        ExpandableText(
            fullText = "I feel so catered to. It feels like an eternity since I've been given such a phenomenal anime with a well thought out plot, great art and animation to accompany it, emotional thought provoking moments... more than that, it feels so mature in that it doesn't baby me with your typical anime tropes. No obnoxiously screaming kid protags, no overly ecchi scenes meant purely for fanservice, no moments where I have to groan and just deal with whatever stupidity is put before me-- Just a fun and emotional journey that is pure story and human feelings. I'm not going to go into too incredible detail, but ifyou're a fan of fantasy and pure story elements, this is the anime for you. It focuses on the after story of Frieren, who has already well and finished her main quest to defeat the demon lord that plagued their lands. We join her at the end, celebrating the accomplishments of her and the party she traveled with. Time passing ever so swiftly as her party aged while she does not as an elf. She's always known that she will easily outage everyone surrounding her and did not think twice of how she spent her time in those many years till next they met. And then she had to come to face with reality-- The hero she'd traveled with would die. Her compatriots would surely soon follow. Uncaring as she was before, she now realizes just how much she should have bothered to care.\\n\\nThus does she go on a journey to find herself and to meet up with the others in their older ages. Thus does she take on an apprentice and decide to try and reunite with the hero waiting where they last felled the demon king.\\n\\nWhat anyone may appreciate most will go into just how well these characters are written. Especially the women! Rarely have I seen a well written woman character that I could fall in love with... and I had just about given up hope in such a male protagonist dominated field. You'll come to appreciate that they're their own characters, with their own thoughts and emotions, with varying personalities and things that get to them in a very human way. Sometimes our emotions don't make sense and occasionally you'll see these characters on their bad days, acting out of sorts and it's interesting to see.\\n\\nEverything from the art, to animation, to music, to details... all of it is wonderful on the eyes and ears. When the going gets tough and the fights begin, the battle choreography is just so gorgeous to watch. So much detail and weight goes into everyone's movement. I think my only possible gripe could be that sometimes the characters can come off as a little stiff, their faces of emotion not as exaggerated when it comes to anime standards. Maybe that's a good thing and we're too accustomed to over the top facial expressions after all this time-- but occasionally, one wonders if it's not just a little too lifeless at times.\\n\\nOther than that nitpick though, I can't help but recommend this anime to all of my friends and share in all its glory! My group of friends were particularly entranced and just so pleased to have a more adult show that felt appropriate for our older age. Just don't go into this expecting it to be fast and getting into this for that quick hit of awesome epic moments. This is a slow ride and an enjoyable one as it builds its world and relationships at a relaxing pace."
        )

        /*Text(
            "I feel so catered to. It feels like an eternity since I've been given such a phenomenal anime with a well thought out plot, great art and animation to accompany it, emotional thought provoking moments... more than that, it feels so mature in that it doesn't baby me with your typical anime tropes. No obnoxiously screaming kid protags, no overly ecchi scenes meant purely for fanservice, no moments where I have to groan and just deal with whatever stupidity is put before me-- Just a fun and emotional journey that is pure story and human feelings. I'm not going to go into too incredible detail, but ifyou're a fan of fantasy and pure story elements, this is the anime for you. It focuses on the after story of Frieren, who has already well and finished her main quest to defeat the demon lord that plagued their lands. We join her at the end, celebrating the accomplishments of her and the party she traveled with. Time passing ever so swiftly as her party aged while she does not as an elf. She's always known that she will easily outage everyone surrounding her and did not think twice of how she spent her time in those many years till next they met. And then she had to come to face with reality-- The hero she'd traveled with would die. Her compatriots would surely soon follow. Uncaring as she was before, she now realizes just how much she should have bothered to care.\\n\\nThus does she go on a journey to find herself and to meet up with the others in their older ages. Thus does she take on an apprentice and decide to try and reunite with the hero waiting where they last felled the demon king.\\n\\nWhat anyone may appreciate most will go into just how well these characters are written. Especially the women! Rarely have I seen a well written woman character that I could fall in love with... and I had just about given up hope in such a male protagonist dominated field. You'll come to appreciate that they're their own characters, with their own thoughts and emotions, with varying personalities and things that get to them in a very human way. Sometimes our emotions don't make sense and occasionally you'll see these characters on their bad days, acting out of sorts and it's interesting to see.\\n\\nEverything from the art, to animation, to music, to details... all of it is wonderful on the eyes and ears. When the going gets tough and the fights begin, the battle choreography is just so gorgeous to watch. So much detail and weight goes into everyone's movement. I think my only possible gripe could be that sometimes the characters can come off as a little stiff, their faces of emotion not as exaggerated when it comes to anime standards. Maybe that's a good thing and we're too accustomed to over the top facial expressions after all this time-- but occasionally, one wonders if it's not just a little too lifeless at times.\\n\\nOther than that nitpick though, I can't help but recommend this anime to all of my friends and share in all its glory! My group of friends were particularly entranced and just so pleased to have a more adult show that felt appropriate for our older age. Just don't go into this expecting it to be fast and getting into this for that quick hit of awesome epic moments. This is a slow ride and an enjoyable one as it builds its world and relationships at a relaxing pace.",
            fontWeight = FontWeight(400),
            fontSize = 12.sp,
            lineHeight = 20.sp,
            color = colorResource(R.color.gray4)
        )*/


    }
}


@Composable
fun RatingBar(rating: Int) {
    val starPainter = painterResource(id = R.drawable.ic_star_full)

    repeat(5) { index ->
        val isFilled = index < rating
        Icon(
            painter = starPainter,
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = if (isFilled) Color.Yellow else Color.LightGray
        )
    }
}

@Composable
fun ExpandableText(
    fullText: String,
    minimizedMaxLines: Int = 5,
) {
    var expanded by remember { mutableStateOf(false) }

    val maxLines = if (expanded) Int.MAX_VALUE else minimizedMaxLines

    val toggleExpanded: () -> Unit = { expanded = !expanded }

    var isTextClipped by remember { mutableStateOf(false) }

    Column {
        Text(
            text = fullText,
            modifier = Modifier.clickable(onClick = { toggleExpanded() }),
            maxLines = maxLines,
            color = colorResource(R.color.gray4),
            fontWeight = FontWeight(400),
            fontSize = 12.sp,
            lineHeight = 20.sp,
            onTextLayout = { textLayoutResult ->
                if (!expanded) {
                    isTextClipped = textLayoutResult.hasVisualOverflow
                }
            },
        )

        if (isTextClipped || expanded) {
            Text(
                text = if (expanded) " 접기" else "...more",
                modifier = Modifier.clickable(onClick = toggleExpanded),
                color = colorResource(R.color.TransparencyWhite),
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Composable
@Preview
fun ReviewTabPreview() {
    ReviewItem()
}