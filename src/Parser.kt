import java.util.*

// This class parser is used to break the code into different components and it should know type of command
class Parser(var s: Scanner) {

    private var currentCommand = ""

    var myMap = HashMap<Int,String>()

    fun hasMoreCommands(): Boolean {
        return s.hasNextLine()//this returns true if there is another line found in the input
    }
    fun reset() {
        s = s.reset()
    }

    fun advance() {
        if (hasMoreCommands()) {
            currentCommand = s.nextLine()

            // Inorder To remove   comments
            val commentIndex = currentCommand.indexOf("/")
            //index of returns the position where first '/' is found

            if (commentIndex >= 0)
            {
                currentCommand = currentCommand.substring(0, commentIndex)
            }

            // strip out whitespace
            //Returns a new string obtained by replacing all occurrences of the oldValue (return)
            currentCommand = currentCommand.replace(" ", "")

            if (currentCommand.isBlank()) {
                advance()
            }
        }
    }
//To determine the command type
    fun commandType(): CommandType
    {//It returns the constants if starts with mentioned Symbol
        if (currentCommand.startsWith("@")) {
            return CommandType.A_COMMAND
        }
        //Labels
        if (currentCommand.startsWith("(")) {
            return CommandType.L_COMMAND
        }

        return CommandType.C_COMMAND
    }

    fun symbol(): String {
        if(commandType() == CommandType.A_COMMAND) {
            return currentCommand.substring(1)//@ is removed here
        }

        if(commandType() == CommandType.L_COMMAND) {
            return currentCommand.substring(1, currentCommand.length - 1)//() is removed here
        }

        return ""
    }

    fun dest(): String {
        if (commandType() == CommandType.C_COMMAND) {
            val temp = currentCommand.split("=")[0]//stores the value before equal (=)
            return if (currentCommand.contains(";")) ""//if not dest
            else temp
        }

        return ""
    }

    fun comp(): String {
        if (commandType() == CommandType.C_COMMAND) {
            val temp = if (currentCommand.contains("="))
                currentCommand.split("=")[1]
            else currentCommand
            return temp.split(";")[0]
        }

        return ""
    }

    fun jump(): String {
        if (commandType() == CommandType.C_COMMAND && currentCommand.contains(";"))
        {
            return currentCommand.split(";")[1]//to return JMP and JGT
        }

        return ""
    }

    fun integerToBinary(integer: String): String//to convert to binary
    {
        var binary = Integer.toBinaryString(Integer.parseInt(integer))
        while (binary.length < 15) {
            binary = "0" + binary
        }
        return binary
    }


}