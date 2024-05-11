// // calculator program

// // constants for the programs
// const conjunction = '∧';
// const disjuction = '∨';
// const tautology = 'T';
// const contradiction = 'F';

// let display = 'p∧q';
// console.log(display);

// // let display = document.getElementById("display");
// // let valueInMemory = '';

// // // adding to the value or string on display
// // function appendToDisplay(input){
// //     display.value += input;

// // }

// // // CLEAR DISPLAY
// // function clearDisplay(){
// //     // clearing the whole display
// //     // also clears the memory
// //     display.value = "";

// // }
// // // OKAY BUTTON
// // // Temporarily storing the value so that the user can be able to switch back
// // function storeValue(input){
// //     valueInMemory = display.value;
    
// // }
// // // RETURN TO ORIGINAL VALUE
// // // pressing the back button, goes to the original expressin
// // function returnBack(input){
// //     display.value = valueInMemory;
// // }

// // function calculate(){
// //     display.value = eval(display.value)

// // }
// // // DELETE LAST VALUE
// // function deleteValue(input){
// //     display.value = display.value.toString().slice(0, -1);
// // }

// // DUAL FUNCTION
// function toDual(){
//     //declare a new display variable
//     let dualVar = '';
//     let tempDisplay = display;
//     let lengthOfDisplay = display.length;

//     for(let i = 0; i < lengthOfDisplay; i++){
//         // if the character is a conjuction
//         if (tempDisplay.charAt(i) == conjunction){
//             //replace it with a disjuction
//             dualVar += disjuction;

//         }
//         else if (tempDisplay.charAt(i) == disjuction){
//             dualVar += conjunction;
//         }
//         else if (tempDisplay.charAt(i) == contradiction){
//             dualVar += tautology;
//         }
//         else if (tempDisplay.charAt(i) == tautology){
//             dualVar += tautology;
//         }
//         else{
//             dualVar += tempDisplay.charAt(i);
//         }
//     }
//     console.log("hello");

//     console.log(display);
//     display = dualVar;
//     console.log(display);

// }

// toDual();

