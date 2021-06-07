import java.io.File
import java.util.*


fun main(args: Array<String>) {
    val Asmfile = File("RectL.asm")
    val t = Scanner(Asmfile)

    val p1 = Parser(t)
    val code = Code()

    val HackFile = File("RectL.asm".split(".asm")[0] + ".hack")

    //  To clean the output file
    HackFile.writeText("")

    // First Pass is done to fill Symbol Table with labels
    var symboltable = SymbolTable()
    var address = 0
    while(p1.hasMoreCommands()) {
        p1.advance()

        if (p1.commandType() == CommandType.L_COMMAND) {
            symboltable.addEntry(p1.symbol(), address)
        } else {
            address++
        }
    }

    // Second Pass
    val t2 = Scanner(Asmfile)
    val p2 = Parser(t2)
    address = 16
    while(p2.hasMoreCommands()) {
        p2.advance()

        if (p2.commandType() == CommandType.A_COMMAND) {
            // write empty line for now
            // check for Label of number
            try {
                p2.symbol().toDouble()
                HackFile.appendText("0" + p2.integerToBinary(p2.symbol()) + "\n")
            } catch(e: NumberFormatException) {
                if (!symboltable.contains(p2.symbol())) {
                    symboltable.addEntry(p2.symbol(), address)//adding new symbol
                    address++
                }



                        HackFile.appendText("0" + p2.integerToBinary("" + symboltable.getAddress(p2.symbol())) + "\n")
            }

        }
//Since C command starts with 3 1's
        if (p2.commandType() == CommandType.C_COMMAND) {
            HackFile.appendText("111" + code.comp(p2.comp()) + code.dest(p2.dest()) + code.jump(p2.jump()) + "\n")
        }  //returns the value
    }

}