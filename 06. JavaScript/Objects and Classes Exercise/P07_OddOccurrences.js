function solve(input) {
   let words = input.toLowerCase().split(" ");
   let wordCount = new Map();

   for (let word of words) {
       if (!wordCount.has(word)) {
           wordCount.set(word, 1);
       } else {
           wordCount.set(word, wordCount.get(word) + 1);
       }
   }

   let result = "";
   for (let [key, value] of wordCount.entries()) {
       if (value % 2 === 1) {
           result += key + " ";
       }
   }

   console.log(result.trim());
}

solve('Java C# Php PHP Java PhP 3 C# 3 1 5 C#');
solve('Cake IS SWEET is Soft CAKE sweet Food');