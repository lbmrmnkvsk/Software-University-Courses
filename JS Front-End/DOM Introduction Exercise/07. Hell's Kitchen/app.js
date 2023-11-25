function solve() {
   document.querySelector('#btnSend').addEventListener('click', onClick);

   function onClick() {
      let inputTextarea = document.querySelector('textarea');
      let restaurantsData = inputTextarea.value.split('\n').filter(line => line.trim() !== '');

      let restaurants = {};

      for (let restaurantData of restaurantsData) {
         let [restaurantName, workersData] = restaurantData.split(' - ');
         let workers = workersData.split(', ');

         if (!restaurants.hasOwnProperty(restaurantName)) {
            restaurants[restaurantName] = {
               workers: [],
               avgSalary: 0,
               bestSalary: 0,
            };
         }

         for (let worker of workers) {
            let [workerName, salary] = worker.split(' ');
            salary = Number(salary);

            restaurants[restaurantName].workers.push({ name: workerName, salary });
            restaurants[restaurantName].avgSalary += salary;

            if (salary > restaurants[restaurantName].bestSalary) {
               restaurants[restaurantName].bestSalary = salary;
            }
         }
      }

      // Calculate average salary for each restaurant
      for (let restaurantName in restaurants) {
         let restaurant = restaurants[restaurantName];
         restaurant.avgSalary /= restaurant.workers.length;
      }

      // Find the best restaurant
      let bestRestaurant = null;

      for (let restaurantName in restaurants) {
         if (bestRestaurant === null || restaurants[restaurantName].avgSalary > bestRestaurant.avgSalary) {
            bestRestaurant = {
               name: restaurantName,
               avgSalary: restaurants[restaurantName].avgSalary.toFixed(2),
               bestSalary: restaurants[restaurantName].bestSalary.toFixed(2),
               workers: restaurants[restaurantName].workers,
            };
         }
      }

      // Sort workers by salary in descending order
      bestRestaurant.workers.sort((a, b) => b.salary - a.salary);

      // Display the results
      displayResults(bestRestaurant);
   }

   function displayResults(bestRestaurant) {
      let bestRestaurantOutput = document.getElementById('bestRestaurant');
      bestRestaurantOutput.querySelector('span').textContent = `Name: ${bestRestaurant.name} Average Salary: ${bestRestaurant.avgSalary} Best Salary: ${bestRestaurant.bestSalary}`;

      let workersOutput = document.getElementById('workers');
      workersOutput.querySelector('span').textContent = 'Name:';

      for (let worker of bestRestaurant.workers) {
         workersOutput.querySelector('p').textContent += ` ${worker.name} With Salary: ${worker.salary}`;
      }
   }
}
