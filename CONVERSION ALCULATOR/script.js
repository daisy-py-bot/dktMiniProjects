// calculator program

// constants for the propositional logic
const conjunction = '∧';
const disjuction = '∨';
const tautology = 'T';
const contradiction = 'F';

// constants for set theory
const emptySet = '∅';
const universalSet = 'U';
const union = '∪';
const intersection = '∩';

// constants for boolean logic
const zeroProperty = '0';
const unitProperty = '1';
const conjunctiveProperty = '';
const disjuctiveProperty = '+';

const display = document.getElementById("display");

// where original value stays
let valueInMemory = '';

// keep current answer
let answer = '';

// adding to the value or string on display
function appendToDisplay(input){
    display.value += input;

}

// DISPLAY ANSWER
function displayAnswer(){
    display.value = answer;
}

// CLEAR DISPLAY AND MEMORY
function clearDisplay(){
    // clearing the whole display
    // also clears the memory
    display.value = "";
    valueInMemory = '';

}
// OKAY BUTTON
// Temporarily storing the value so that the user can be able to switch back
function storeValue(input){
    valueInMemory = display.value;
    
}
// RETURN TO ORIGINAL VALUE
// pressing the back button, goes to the original expressin
function returnBack(input){
    display.value = valueInMemory;
}

function calculate(){
    display.value = eval(display.value)

}
// DELETE LAST VALUE
function deleteValue(input){
    display.value = display.value.toString().slice(0, -1);
}

// DUAL FUNCTION
function toDual(){
    //declare a new display variable
    let dualVar = '';
    let tempDisplay = display.value;
    let lengthOfDisplay = tempDisplay.length;

    for(let i = 0; i < lengthOfDisplay; i++){
        // if the character is a conjuction
        if (tempDisplay.charAt(i) == conjunction){
            //replace it with a disjuction
            dualVar += disjuction;

        }
        else if (tempDisplay.charAt(i) == disjuction){
            dualVar += conjunction;
        }
        else if (tempDisplay.charAt(i) == contradiction){
            dualVar += tautology;
        }
        else if (tempDisplay.charAt(i) == tautology){
            dualVar += contradiction;
        }
        else{
            dualVar += tempDisplay.charAt(i);
        }
    }
    answer = dualVar;
    display.value = valueInMemory + '=' + dualVar;
}

//  CONVERT TO SET FUNCTION
function toSet(){
    // create a temp value for display
    let tempStr = valueInMemory;

    // replace values for propositional logic with those for sets
    // complements
    tempStr = tempStr.replace(/¬p/g, 'A`');
    tempStr = tempStr.replace(/¬q/g, 'B`');
    tempStr = tempStr.replace(/¬r/g, 'C`');
    tempStr = tempStr.replace(/¬T/g, emptySet);
    tempStr = tempStr.replace(/¬F/, universalSet);
    tempStr = tempStr.replace(/¬s/g, 'D`');
    tempStr = tempStr.replace(/¬t/g, 'E`');
    tempStr = tempStr.replace(/¬u/g, 'F`');
    tempStr = tempStr.replace(/¬w/g, 'G`');

    // normal variables
    tempStr = tempStr.replace(/p/g, 'A');
    tempStr = tempStr.replace(/q/g, 'B');
    tempStr = tempStr.replace(/r/g, 'C');
    tempStr = tempStr.replace(/F/g, emptySet);
    tempStr = tempStr.replace(/T/g, universalSet);
    tempStr = tempStr.replace(/s/g, 'D');
    tempStr = tempStr.replace(/t/g, 'E');
    tempStr = tempStr.replace(/u/g, 'F');
    tempStr = tempStr.replace(/w/, 'G');

    // connectives
    tempStr = tempStr.replace(/∧/g, intersection);
    tempStr = tempStr.replace(/∨/g, union);
   
    answer = tempStr;
    display.value = valueInMemory + '=' + tempStr;
}

// CONVERT TO BOOLEAN FUNCTION
function toBoolean(){
    let tempStr = valueInMemory;

    tempStr = tempStr.replace(/¬p/g, 'x`');
    tempStr = tempStr.replace(/¬q/g, 'y`');
    tempStr = tempStr.replace(/¬r/g, 'z`');
    tempStr = tempStr.replace(/¬T/g, zeroProperty);
    tempStr = tempStr.replace(/¬F/g, unitProperty);

    // normal variables
    tempStr = tempStr.replace(/p/g, 'x');
    tempStr = tempStr.replace(/q/g, 'y');
    tempStr = tempStr.replace(/r/g, 'z');
    tempStr = tempStr.replace(/F/g, zeroProperty);
    tempStr = tempStr.replace(/T/g, unitProperty);
    

    // connectives
    tempStr = tempStr.replace(/∧/g, conjunctiveProperty);
    tempStr = tempStr.replace(/∨/g, disjuctiveProperty);

    answer = tempStr;
    display.value = valueInMemory + '=' + tempStr;


}



