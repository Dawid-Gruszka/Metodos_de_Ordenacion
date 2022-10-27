fun main() {
    var array = IntArray(100_000) {(0..100_000).random()}
    var timeIni = 0L
    var timeFin = 0L
    var clonadoburbuja = clone(array)
    var clonadoseleccion = clone(array)
    var clonadoinsercion = clone(array)
    var clonadoshell = clone(array)
    var clonadoquick = clone(array)
    var clonadobuscar = clone(array)

    //Ordenado por Burbuja
    var vectorBurbuja = clonadoburbuja
    timeIni = System.currentTimeMillis()
    bubbleSort(vectorBurbuja)
    timeFin = System.currentTimeMillis()
    println("Tiempo burbuja: ${timeFin - timeIni} ms")

    //Ordenado por Seleccion

    val vectorSeleccion = clonadoseleccion
     timeIni = System.currentTimeMillis()
    selectionSort(vectorSeleccion)
    timeFin = System.currentTimeMillis()
    println("Tiempo selección: ${timeFin - timeIni} ms")

    //Ordenado por inserción

    val vectorInsercion = clonadoinsercion
    timeIni = System.currentTimeMillis()
    insertionSort(vectorInsercion)
    timeFin = System.currentTimeMillis()
    println("Tiempo inserción: ${timeFin - timeIni} ms")

    //Ordenado por Shell

    val vectorShell = clonadoshell
    timeIni = System.currentTimeMillis()
    shellSort(vectorShell)
    timeFin = System.currentTimeMillis()
    println("Tiempo shell: ${timeFin - timeIni} ms")

    //Ordenado por Quick

    val vectorQuick = clonadoquick
    timeIni = System.currentTimeMillis()
    quickSort(vectorQuick)
    timeFin = System.currentTimeMillis()
    println("Tiempo quick: ${timeFin - timeIni} ms")

    //Busqueda lineal o secuencial

    val elementoParaBuscar = clonadobuscar.random()

    timeIni = System.nanoTime()
    linearSearch(array, elementoParaBuscar)
    timeFin = System.nanoTime()
    println("Tiempo búsqueda lineal: ${timeFin - timeIni} ns")


    //Busqueda binaria

    val vectorOrdenado = array.sortedArray()
    timeIni = System.nanoTime()
    binarySearch(vectorOrdenado, elementoParaBuscar)
    timeFin = System.nanoTime()
    println("Tiempo búsqueda binaria: ${timeFin - timeIni} ns")


}

fun clone(array: IntArray): IntArray {
    val arrayClone = IntArray(array.size)
    for (i in 0 until  array.size){
        arrayClone[i] = array[i]
    }
    return arrayClone
}
fun bubbleSort(vector: IntArray) {
    for (i in 0 until vector.size - 1) {
        for (j in 0 until vector.size - 1) {
            if (vector[j] > vector[j + 1]) {
                val aux = vector[j]
                vector[j] = vector[j + 1]
                vector[j + 1] = aux
            }
        }
    }
}
fun selectionSort(array: IntArray) {
    for (i in 0 until array.size - 1) {
        var min = i
        for (j in i + 1 until array.size) {
            if (array[j] < array[min]) {
                min = j
            }
        }
        val aux = array[i]
        array[i] = array[min]
        array[min] = aux
    }
}
fun insertionSort(array: IntArray) {
    for (i in 1 until array.size) {
        var j = i
        while (j > 0 && array[j] < array[j - 1]) {
            val aux = array[j]
            array[j] = array[j - 1]
            array[j - 1] = aux
            j--
        }
    }
}
fun shellSort(array: IntArray) {
    var h = 1
    while (h < array.size / 3) {
        h = 3 * h + 1
    }
    while (h >= 1) {
        for (i in h until array.size) {
            var j = i
            while (j >= h && array[j] < array[j - h]) {
                val aux = array[j]
                array[j] = array[j - h]
                array[j - h] = aux
                j -= h
            }
        }
        h /= 3
    }
}
fun pivot(array: IntArray, left: Int, right: Int): Int {
    var i = left
    var j = right
    var pivot = array[left]
    while (i < j) {
        while (array[i] <= pivot && i < j) {
            i++
        }
        while (array[j] > pivot) {
            j--
        }
        if (i < j) {
            val aux = array[i]
            array[i] = array[j]
            array[j] = aux
        }
    }
    array[left] = array[j]
    array[j] = pivot
    return j
}
fun quicksort(array: IntArray, left: Int, right: Int) {
    val piv: Int
    if (left < right) {
        piv = pivot(array, left, right)
        quicksort(array, left, piv - 1)
        quicksort(array, piv + 1, right)
    }
}
fun quickSort(array: IntArray) {
    quicksort(array, 0, array.size - 1)
}
fun linearSearch(array: IntArray, value: Int): Int {
    for (i in array.indices) {
        if (array[i] == value) {
            return i
        }
    }
    return -1
}
fun binarySearch(array: IntArray, value: Int, left: Int, right: Int): Int {
    if (left > right) {
        return -1
    }
    val middle = (left + right) / 2
    return when {
        array[middle] == value -> middle
        array[middle] > value -> binarySearch(array, value, left, middle - 1)
        else -> binarySearch(array, value, middle + 1, right)
    }
}

fun binarySearch(array: IntArray, value: Int): Int {
    return binarySearch(array, value, 0, array.size - 1)
}
