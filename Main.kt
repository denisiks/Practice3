sealed class Result<T>
class Success<T>(val data: T) : Result<T>()
class Error<T>(val errorString: String) : Result<T>()

enum class Operation {
    SORT_ASC
    {
        override fun <T : Comparable<T>> invoke(List: List<T>) = List.sorted()
    },
    SORT_DESC
    {
        override fun <T : Comparable<T>> invoke(List: List<T>) = List.sortedDescending()
    },
    SHUFFLE
    {
        override fun <T : Comparable<T>> invoke(List: List<T>) = List.shuffled()
    };
    abstract operator fun <T : Comparable<T>> invoke(List: List<T>): List<T>
}

fun <T : Comparable<T>> List<T>.operate(operation: Operation) =
    if (this.isNotEmpty())
    {
        when (operation)
        {
            Operation.SORT_ASC -> Success(Operation.SORT_ASC(this))
            Operation.SORT_DESC -> Success(Operation.SORT_DESC(this))
            Operation.SHUFFLE -> Success(Operation.SHUFFLE(this))
        }
    }
    else Error("Список пуст")

fun generateStrings(stringLength: Int, length: Int): List<String> {
    val chars = ('a'..'z')
    val rstring = mutableListOf<String>()
    for (i in 1..length)
    {
        rstring.add((1..stringLength).map { chars.random() }.joinToString(""))
    }
        return rstring
}

fun generateIntegers(length: Int) = (0..999).shuffled().take(length)

fun <T : Comparable<T>> Result<List<T>>.print() {
     if (this is Success) println(this.data)
     else if (this is Error) println(this.errorString)
}
fun main() {
    generateStrings(4, 4).operate(Operation.SORT_ASC).print()
    generateStrings(4, 4).operate(Operation.SORT_DESC).print()
    generateStrings(4, 4).operate(Operation.SHUFFLE).print()
    generateIntegers(4).operate(Operation.SORT_ASC).print()
    generateIntegers(4).operate(Operation.SORT_DESC).print()
    generateIntegers(4).operate(Operation.SHUFFLE).print()
}