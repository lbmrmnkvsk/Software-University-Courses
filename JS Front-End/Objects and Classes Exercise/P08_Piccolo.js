function solve(input) {
    let parkingLot = new Set();

    for (let line of input) {
        let [direction, number] = line.split(", ");
        if (direction === "IN") {
            parkingLot.add(number);
        } else if (direction === "OUT") {
            parkingLot.delete(number);
        }
    }

    let sorted = Array.from(parkingLot).sort();
    if (sorted.length > 0) {
        sorted.forEach(c => console.log(c));
    } else {
        console.log("Parking Lot is Empty");
    }
}

solve(['IN, CA2844AA', 'IN, CA1234TA', 'OUT, CA2844AA', 'IN, CA9999TT', 'IN, CA2866HI', 'OUT, CA1234TA', 'IN, CA2844AA', 'OUT, CA2866HI', 'IN, CA9876HH', 'IN, CA2822UU']);
solve(['IN, CA2844AA', 'IN, CA1234TA', 'OUT, CA2844AA', 'OUT, CA1234TA']);