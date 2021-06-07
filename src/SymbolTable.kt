class SymbolTable {

    val Mapto: HashMap<String, Int> = HashMap()
//Here I have used init is used initializes its properties
    init {
        Mapto.put("SP", 0)
        Mapto.put("LCL", 1)
        Mapto.put("ARG", 2)
        Mapto.put("THIS", 3)
        Mapto.put("THAT", 4)
        Mapto.put("R0", 0)
        Mapto.put("R1", 1)
        Mapto.put("R2", 2)
        Mapto.put("R3", 3)
        Mapto.put("R4", 4)
        Mapto.put("R5", 5)
        Mapto.put("R6", 6)
        Mapto.put("R7", 7)
        Mapto.put("R8", 8)
        Mapto.put("R9", 9)
        Mapto.put("R10", 10)
        Mapto.put("R11", 11)
        Mapto.put("R12", 12)
        Mapto.put("R13", 13)
        Mapto.put("R14", 14)
        Mapto.put("R15", 15)
        Mapto.put("SCREEN", 16384)
        Mapto.put("KBD", 24576)
    }




//if we want to add new value to the HashMap Mapto
    fun addEntry(symbol: String, address: Int) {
        Mapto.put(symbol, address)
    }
//Returns true if this map contains a mapping for the specified key
    fun contains(symbol: String): Boolean {
        return Mapto.containsKey(symbol)
    }
// This returns the value of the symbol,eg:R0 returns 0 here
    fun getAddress(symbol: String): Int {
        return Mapto.getValue(symbol)
    }
}