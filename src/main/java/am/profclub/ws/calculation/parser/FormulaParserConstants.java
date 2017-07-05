/* Generated By:JavaCC: Do not edit this line. FormulaParserConstants.java */
package am.profclub.ws.calculation.parser;


/**
 * Token literal values and constants.
 * Generated by org.javacc.parser.OtherFilesGen#start()
 */
public interface FormulaParserConstants {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int OPEN_PAREN = 6;
  /** RegularExpression Id. */
  int CLOSE_PAREN = 7;
  /** RegularExpression Id. */
  int COMMA = 8;
  /** RegularExpression Id. */
  int DOT = 9;
  /** RegularExpression Id. */
  int PLUS = 10;
  /** RegularExpression Id. */
  int MINUS = 11;
  /** RegularExpression Id. */
  int MULTIPLY = 12;
  /** RegularExpression Id. */
  int DIVIDE = 13;
  /** RegularExpression Id. */
  int MODULUS = 14;
  /** RegularExpression Id. */
  int AND = 15;
  /** RegularExpression Id. */
  int OR = 16;
  /** RegularExpression Id. */
  int NOT = 17;
  /** RegularExpression Id. */
  int BIT_AND = 18;
  /** RegularExpression Id. */
  int BIT_OR = 19;
  /** RegularExpression Id. */
  int BIT_XOR = 20;
  /** RegularExpression Id. */
  int BIT_NOT = 21;
  /** RegularExpression Id. */
  int EQUAL = 22;
  /** RegularExpression Id. */
  int NOT_EQUAL = 23;
  /** RegularExpression Id. */
  int LESS_THAN = 24;
  /** RegularExpression Id. */
  int GREATER_THAN = 25;
  /** RegularExpression Id. */
  int LESS_EQUAL = 26;
  /** RegularExpression Id. */
  int GREATER_EQUAL = 27;
  /** RegularExpression Id. */
  int BOOLEAN_LITERAL = 28;
  /** RegularExpression Id. */
  int INTEGER_LITERAL = 29;
  /** RegularExpression Id. */
  int FLOAT_LITERAL = 30;
  /** RegularExpression Id. */
  int STRING_LITERAL = 31;
  /** RegularExpression Id. */
  int NULL = 32;
  /** RegularExpression Id. */
  int IDENTIFIER = 33;
  /** RegularExpression Id. */
  int DE_FIELD = 34;
  /** RegularExpression Id. */
  int FIELD = 35;
  /** RegularExpression Id. */
  int DATEWILDCARD = 36;
  /** RegularExpression Id. */
  int WILDCARD = 37;

  /** Lexical state. */
  int DEFAULT = 0;

  /** Literal token values. */
  String[] tokenImage = {
    "<EOF>",
    "\" \"",
    "\"\\t\"",
    "\"\\n\"",
    "\"\\r\"",
    "\"\\f\"",
    "\"(\"",
    "\")\"",
    "\",\"",
    "\".\"",
    "\"+\"",
    "\"-\"",
    "\"*\"",
    "\"/\"",
    "<MODULUS>",
    "<AND>",
    "<OR>",
    "<NOT>",
    "\"&\"",
    "\"|\"",
    "\"^\"",
    "\"~\"",
    "<EQUAL>",
    "\"!=\"",
    "\"<\"",
    "\">\"",
    "\"<=\"",
    "\">=\"",
    "<BOOLEAN_LITERAL>",
    "<INTEGER_LITERAL>",
    "<FLOAT_LITERAL>",
    "<STRING_LITERAL>",
    "\"null\"",
    "<IDENTIFIER>",
    "<DE_FIELD>",
    "<FIELD>",
    "<DATEWILDCARD>",
    "<WILDCARD>",
  };

}