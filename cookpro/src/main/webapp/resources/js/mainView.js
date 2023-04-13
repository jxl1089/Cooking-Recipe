let leftBtn = [...document.getElementsByClassName("slider-leftBtn")]
    let rightBtn = [...document.getElementsByClassName("slider-rightBtn")]
    let slider = [...document.querySelectorAll(".card-container")]
    let sliderDimension = document.querySelector(".card-container")


    slider.forEach((item, i) => {
        let containerDimension = item.getBoundingClientRect().width;
        if ((item.childElementCount * 350) < (containerDimension + 100)) {
            rightBtn[i].style.display = 'none';
            leftBtn[i].style.display = 'none';
            item.style.justifyContent = 'space-around';
        }
        leftBtn[i].style.display = "none"
        rightBtn[i].addEventListener('click', () => {
            item.scrollLeft += containerDimension;
            leftBtn[i].style.display = 'block';
        })
        leftBtn[i].addEventListener('click', () => {
            item.scrollLeft -= containerDimension;
            rightBtn[i].style.display = 'block';
        })
        item.addEventListener('scroll', () => {
            if (item.offsetWidth + item.scrollLeft >= (item.scrollWidth - 100)) {
                rightBtn[i].style.display = 'none';
                leftBtn[i].style.display = 'block';
            } else if (item.scrollLeft == 0) {
                leftBtn[i].style.display = 'none';
                rightBtn[i].style.display = 'block';
            } else if (item.scrollLeft > 0) {
                leftBtn[i].style.display = 'block';
            }
            if (item.offsetWidth + item.scrollLeft < (item.scrollWidth - 50)) {
                rightBtn[i].style.display = 'block';
            }
        })

        const slidingFunction = () => {
            if (item.offsetWidth + item.scrollLeft >= (item.scrollWidth - 100)) {
                // item.style.scrollBehavior = "auto";
                item.scrollLeft = 0;
            }
            if (item.offsetWidth + item.scrollLeft < (item.scrollWidth - 50)) {
                // item.style.scrollBehavior = "smooth";
                item.scrollLeft += containerDimension;
            }
        }

        setInterval(() => slidingFunction(), 5000)
    })