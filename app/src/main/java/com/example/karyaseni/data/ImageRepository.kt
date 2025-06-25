package com.example.karyaseni.data

import androidx.compose.runtime.mutableStateListOf
import com.example.karyaseni.ImageModel

// Repository untuk menyimpan data dummy
object ImageRepository {
    private val imageList = mutableStateListOf<ImageModel>()
    init {
        imageList.addAll(getDummyImages())
    }
    fun getImages(): List<ImageModel> = imageList

    fun addImage(image: ImageModel) {
        imageList.add(0, image) // add to top
    }
    fun getDummyImages(): List<ImageModel> {
        return listOf(
            ImageModel(
                id = "1",
                title = "Unsupervised Learning",
                imageUri = "https://i.imgur.com/8Y8arY8.png",
                description = "Agglomerative Clustering Scatter Plot with Centoroids",
                uploader = "Me",
                date = "23 Maret 2025"
            ),
            ImageModel(
                id = "2",
                title = "Event",
                imageUri = "https://i.ibb.co.com/pjyTYhR2/PXL-20230910-041754167-2.jpg",
                description = "Event Wakuwaku (wibu x wota)",
                uploader = "Me",
                date = "22 April 2025"
            ),
            ImageModel(
                id = "3",
                title = "Hikawa Sister",
                imageUri = "https://static.wikia.nocookie.net/bandori/images/f/f2/Boppin%27_Twinsies%E2%99%AA.png/revision/latest?cb=20231030124252",
                description = "Card Name: Boppin' Twinsies",
                uploader = "Hxkry",
                date = "21 April 2025"
            ),
            ImageModel(
                id = "4",
                title = "TC ITS",
                imageUri = "https://i.imgur.com/e32oJzW.jpg",
                description = "Gedung Teknik Informatika ITS",
                uploader = "Me",
                date = "20 April 2025"
            ),
            ImageModel(
                id = "5",
                title = "Home Linux",
                imageUri = "https://i.imgur.com/vXUDjvl.png",
                description = "Tampilan Home Linux Akmal Sulthon",
                uploader = "Maru",
                date = "19 April 2025"
            ),
            ImageModel(
                id = "6",
                title = "KOCHENG",
                imageUri = "https://i.imgur.com/NaOxukE.jpg",
                description = "Kucing duduk seenaknya di atas laptop",
                uploader = "Me",
                date = "18 April 2025"
            ),
            ImageModel(
                id = "7",
                title = "Istri Akmal",
                imageUri = "https://pbs.twimg.com/media/GoWQOwgXMAAhWaY?format=jpg&name=large",
                description = "Oshi akmal sulthon yang berasal dari agensi jkt48",
                uploader = "Maru",
                date = "17 April 2025"
            ),
            ImageModel(
                id = "8",
                title = "Istri Khosyi'",
                imageUri = "https://i.pinimg.com/736x/0c/80/85/0c8085a3557ea00190f70506c874379c.jpg",
                description = "Oshi khosyi yang berasal dari agensi bushiroad",
                uploader = "Hxkry",
                date = "16 April 2025"
            ),
            ImageModel(
                id = "9",
                title = "The Herta",
                imageUri = "https://i.pinimg.com/736x/49/bb/1b/49bb1b3e21cede419f6de04567066929.jpg",
                description = "The Herta from Honkai Star Rail",
                uploader = "Hxkry",
                date = "15 April 2025"
            ),
            ImageModel(
                id = "10",
                title = "Eika Boom",
                imageUri = "https://i.pinimg.com/736x/75/91/dd/7591dd88de5f98a5fd8742505eb5d7e7.jpg",
                description = "EIKAAAAA DUAR",
                uploader = "Hxkry",
                date = "14 April 2025"
            ),
            ImageModel(
                id = "11",
                title = "Michele Universe",
                imageUri = "https://i.pinimg.com/736x/cf/d0/ef/cfd0efc9acde275fc034c5e80cf7c023.jpg",
                description = "Cute Michele Chibi seeing universe",
                uploader = "Hxkry",
                date = "16 April 2025"
            ),
            ImageModel(
                id = "12",
                title = "Roselia Live",
                imageUri = "https://i.pinimg.com/736x/cd/30/3d/cd303d2f44ed0dcb056ba0e41caa82f3.jpg",
                description = "Band Roselia Live, foto dari belakang panggung",
                uploader = "Hxkry",
                date = "15 April 2025"
            ),
            ImageModel(
                id = "13",
                title = "Stelle HSR",
                imageUri = "https://i.pinimg.com/736x/b9/5d/49/b95d495e263b7a399de386e7f400266c.jpg",
                description = "Stelle Loves Trashcan",
                uploader = "Hxkry",
                date = "16 April 2025"
            ),
            ImageModel(
                id = "14",
                title = "Rock Garden Japan",
                imageUri = "https://boutiquejapan.com/wp-content/uploads/2020/11/Rock-garden-Japan.jpg",
                description = "Rock Garden Japan",
                uploader = "Charles",
                date = "15 April 2025"
            ),
            ImageModel(
                id = "15",
                title = "Angkor War",
                imageUri = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d4/20171126_Angkor_Wat_4712_DxO.jpg/1546px-20171126_Angkor_Wat_4712_DxO.jpg",
                description = "Angkor War",
                uploader = "Charles",
                date = "16 April 2025"
            ),
            ImageModel(
                id = "16",
                title = "Mario",
                imageUri = "https://cdn.geekwire.com/wp-content/uploads/2019/09/mobile_MarioKartTour_screen_01-630x630.png",
                description = "Mario",
                uploader = "Charles",
                date = "15 April 2025"
            ),
            ImageModel(
                id = "17",
                title = "Shikanoko",
                imageUri = "https://i.pinimg.com/736x/aa/d9/09/aad909c7d6c0831ce000c74fd593d5f3.jpg",
                description = "Shikanoko noko noko koshitan tan",
                uploader = "Hxkry",
                date = "16 April 2025"
            ),
            ImageModel(
                id = "18",
                title = "Surabaya",
                imageUri = "https://i.pinimg.com/736x/03/80/e0/0380e0826d2122e2bf97524c794a980d.jpg",
                description = "Surabaya Lur",
                uploader = "Maru",
                date = "15 April 2025"
            ),
            ImageModel(
                id = "19",
                title = "Mobil",
                imageUri = "https://mclaren.bloomreach.io/delivery/resources/content/gallery/mclaren-racing/formula-1/2025/2025-schedule/miami-gp/race/report/race-report_0004_2213398172-1-2.jpg ",
                description = "Mobil F1",
                uploader = "Charles",
                date = "16 April 2025"
            ),
            ImageModel(
                id = "20",
                title = "Fuji",
                imageUri = "https://i.pinimg.com/736x/4d/73/3b/4d733bcd3e01ba2a865fda47a17bc799.jpg",
                description = "Mount Fuji",
                uploader = "Charles",
                date = "15 April 2025"
            ),
            ImageModel(
                id = "21",
                title = "Mobil Nissan",
                imageUri = "https://static.jdmexport.com/wp-content/uploads/sites/9/2021/12/07090429/Nissan-Skyline-R34.jpeg ",
                description = "Racing",
                uploader = "Charles",
                date = "16 April 2025"
            ),
            ImageModel(
                id = "22",
                title = "Lionel Messi",
                imageUri = "https://i.pinimg.com/736x/8d/2e/b6/8d2eb64bcf174e455372f979c4282656.jpg",
                description = "Messi Barcelona",
                uploader = "Charles",
                date = "15 April 2025"
            ),
            ImageModel(
                id = "23",
                title = "Basket",
                imageUri = "https://www.bardown.com/polopoly_fs/1.1271452.1552322805!/fileimage/httpImage/image.jpg_gen/derivatives/landscape_620/lebron-james-and-dwyane-wade.jpg",
                description = "Lebron",
                uploader = "Charles",
                date = "16 April 2025"
            ),
            ImageModel(
                id = "24",
                title = "Kucing Oren",
                imageUri = "https://images.squarespace-cdn.com/content/v1/607f89e638219e13eee71b1e/1684821560422-SD5V37BAG28BURTLIXUQ/michael-sum-LEpfefQf4rU-unsplash.jpg",
                description = "Kucing lucu",
                uploader = "Charles",
                date = "15 April 2025"
            ),
            ImageModel(
                id = "25",
                title = "New York",
                imageUri = "https://www.offsoho.com/images/NYC_000021208828-2100-980.jpg",
                description = "New York",
                uploader = "Charles",
                date = "16 April 2025"
            ),
            ImageModel(
                id = "26",
                title = "Time Square",
                imageUri = "https://www.foodandwine.com/thmb/NuolVdTqFR82UPwOBUZodWRe2fg=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/times-square-billboards-going-dark-FT-BLOG0520-7cc8310ceb2c49d3ab8056c26c8e78d2.jpg ",
                description = "Time Square",
                uploader = "Charles",
                date = "15 April 2025"
            ),
            ImageModel(
                id = "27",
                title = "Raja Ampat",
                imageUri = "https://res.cloudinary.com/zublu/image/fetch/f_webp,w_1200,q_auto/https://www.zubludiving.com/images/Indonesia/West-Papua/Raja-Ampat/Raja-Ampat-Wayag-Diving.jpg",
                description = "Raja Ampat",
                uploader = "Charles",
                date = "16 April 2025"
            ),
            ImageModel(
                id = "28",
                title = "Eiffel Tower",
                imageUri = "https://www.planetware.com/wpimages/2020/02/france-in-pictures-beautiful-places-to-photograph-eiffel-tower.jpg",
                description = "Eiffel Tower",
                uploader = "Charles",
                date = "15 April 2025"
            ),
            ImageModel(
                id = "29",
                title = "Leonardo dicaprio",
                imageUri = "https://nepalnews.com/wp-content/uploads/2024/10/87091563858de08f3a86140b1daf711a1668221618.jpg",
                description = "Leonardo dicaprio",
                uploader = "Charles",
                date = "16 April 2025"
            ),
            ImageModel(
                id = "30",
                title = "Taylor Swift",
                imageUri = "https://pontianakinfo.disway.id/upload/0d1f592d599d69ac6aec6b074aa5a30f.jpg ",
                description = "Taylor Swift",
                uploader = "Charles",
                date = "15 April 2025"
            )
        )
    }
}