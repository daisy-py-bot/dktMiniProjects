/*More developments
1. Include more rounds on women, politics, economics, sports
2. Add more information on the answer choices and explanations
3. Animate background
4. Welcome page with various categories
5. Add sound when the user enters a correct answer
*/

// add the questions
// this is an array of dictionaries
const questions = [
    {
        question: "What is the largest country in Africa?",
        answers:[
            {text: "Mali", correct: false},
            {text: "Algeria", correct: true},
            {text: "Zimbabwe", correct: false},
            {text: "Chad", correct: false}
        ]

    },
    {
        question: "What is the most populous city in Africa?",
        answers:[
            {text: "Johannesburg", correct: false},
            {text: "Kinshasa", correct: false},
            {text: "Abidjan", correct: false},
            {text: "Lagos", correct: true}
        ]

    },
    {
        question: "What is the most spoken language in Egypt?",
        answers:[
            {text: "English", correct: false},
            {text: "French", correct: false},
            {text: "Arabic", correct: true},
            {text: "Beja", correct: false}
        ]

    },
    {
        question: "What is the largest desert in Africa?",
        answers:[
            {text: "Sahara", correct: true},
            {text: "Nyiri", correct: false},
            {text: "Kalahari", correct: false},
            {text: "Namib", correct: false}
        ]

    },
    {
        question: "The vast majority of Yoruba people live in which country?",
        answers:[
            {text: "Cameroon", correct: false},
            {text: "Togo", correct: false},
            {text: "Ghana", correct: false},
            {text: "Nigeria", correct: true}
        ]

    },
    {
        question: "Madagascar is the only country in the world that is home to which iconic primate?",
        answers:[
            {text: "Lorises", correct: false},
            {text: "Apes", correct: false},
            {text: "Lemurs", correct: true},
            {text: "Tarsiers", correct: false}
        ]

    },
    {
        question: "What is Africa’s largest tropical rainforest?",
        answers:[
            {text: "Upper Guinea Rainforest", correct: false},
            {text: "The Congo Rainforest", correct: true},
            {text: "Eastern Afromontane Rainforest", correct: false},
            {text: "Coastal Forests of Eastern Africa", correct: false}
        ]

    },
    {
        question: "What is the only African country to have hosted the FIFA World Cup?",
        answers:[
            {text: "Ghana", correct: false},
            {text: "South Africa", correct: true},
            {text: "Nigeria", correct: false},
            {text: "Ethiopia", correct: false}
        ]

    },
    {
        question: "Which Southern African peninsular is known for its perilous waters and tales of ghost ships?",
        answers:[
            {text: "Cape Verde", correct: false},
            {text: "Sinai", correct: false},
            {text: "Horn of Africa", correct: false},
            {text: "The Cape of Good Hope", correct: true}
        ]

    },
    {
        question: "With around 200 million speakers, what is the most spoken language on the African continent?",
        answers:[
            {text: "French", correct: false},
            {text: "English", correct: false},
            {text: "Swahili", correct: true},
            {text: "Arabic", correct: false}
        ]

    },
];

// getting the user input by clicking the buttons
const questionElement = document.getElementById("question");
const answerButton = document.getElementById("ans-btns");
const nextButton = document.getElementById("next-btn");

let currentQuestionIndex = 0;
let score = 0;

function startQuiz(){
    currentQuestionIndex = 0;
    score = 0;
    nextButton.innerHTML = "Next";
    showQuestion();
}

function showQuestion(){
    // hide previous question
    resetState();
    let currentQuestion = questions[currentQuestionIndex];
    let questionNo = currentQuestionIndex + 1;
    questionElement.innerHTML = questionNo + ". " + currentQuestion.question;

    currentQuestion.answers.forEach(answer => {
        const button = document.createElement("button");
        button.innerHTML = answer.text;
        button.classList.add("btn");
        answerButton.appendChild(button);

        // adding the click function
        if (answer.correct){
            button.dataset.correct = answer.correct;
        }
        button.addEventListener("click", selectAnswer);
    });
}

// remove previous content
function resetState(){
    nextButton.style.display = "none";
    while(answerButton.firstChild){
        answerButton.removeChild(answerButton.firstChild);
    }
}
function selectAnswer(e){
    const selectedBtn = e.target;
    const isCorrect = selectedBtn.dataset.correct == "true";
    if(isCorrect){
        selectedBtn.classList.add("correct");
        score++;

    }
    else{
        selectedBtn.classList.add("incorrect");
    }

    // displaying the correct button and disabling all the other buttons
    Array.from(answerButton.children).forEach(button => {
        if (button.dataset.correct === "true"){
            button.classList.add("correct");
        }
        button.disabled = true;
    });
    nextButton.style.display = "block";
}

function showScore(){
    resetState();
    questionElement.innerHTML = `You scored ${score} out of ${questions.length}!`;
    nextButton.innerHTML = "Play Again";
    nextButton.style.display = "block";
}
function handleNextButton(){
    currentQuestionIndex++;
    if(currentQuestionIndex < questions.length){
        showQuestion();
    }
    else{
        showScore();
    }
}

//for the next button
nextButton.addEventListener("click", ()=> {
    if(currentQuestionIndex < questions.length){
        handleNextButton();
    }
    else{
        startQuiz();
    }
});
startQuiz();