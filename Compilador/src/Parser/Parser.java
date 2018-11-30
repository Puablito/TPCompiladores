//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 2 "GramaticaTP3.Y"
	package Parser;
	import principal.*;
	import java.util.Stack;	
	import java.util.Map;	
//#line 22 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short IF=257;
public final static short THEN=258;
public final static short ELSE=259;
public final static short ENDIF=260;
public final static short PRINT=261;
public final static short BEGIN=262;
public final static short END=263;
public final static short INT=264;
public final static short ULONG=265;
public final static short WHILE=266;
public final static short DO=267;
public final static short IDENTIFICADOR=268;
public final static short MENOROIGUAL=269;
public final static short DISTINTO=270;
public final static short IGUAL=271;
public final static short MAYOROIGUAL=272;
public final static short ENTERO=273;
public final static short ENTERO_LARGO=274;
public final static short CADENA=275;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    1,    2,    2,    4,    4,    5,    5,
    3,    3,    3,    3,    6,   10,   10,   10,   15,   11,
   16,   11,   11,   11,   14,   14,   18,    7,   17,   17,
   17,   13,   13,   19,   19,   19,   19,    8,    8,    8,
    9,    9,    9,   20,   20,   20,   21,   21,   21,   22,
   22,   22,   22,   12,   12,   12,   12,   12,   23,   23,
   23,   23,   23,   23,   23,
};
final static short yylen[] = {                            2,
    1,    2,    2,    1,    3,    2,    1,    1,    1,    3,
    2,    2,    2,    2,    2,    3,    2,    2,    0,    4,
    0,    3,    2,    1,    2,    1,    0,    3,    3,    2,
    2,    1,    1,    3,    2,    2,    2,    4,    3,    3,
    3,    2,    2,    3,    3,    1,    3,    3,    1,    1,
    1,    2,    1,    5,    4,    4,    4,    4,    1,    1,
    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    7,    8,    0,    0,    0,    1,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    6,   18,
   50,   51,   53,    0,    0,    0,    0,    0,   49,    0,
    0,    0,    0,    2,    3,    9,    0,   11,   12,   13,
   14,    0,   24,    0,    0,   15,    0,   32,    0,    0,
   52,   60,   61,   64,   59,   65,   62,   63,    0,    0,
   16,    0,    0,    0,    0,    0,   39,    0,    0,    5,
    0,   26,    0,   23,   36,    0,    0,   35,   19,    0,
   31,    0,   28,    0,    0,    0,    0,    0,   47,   48,
   38,   10,   25,   34,    0,   22,   29,   57,    0,   56,
   55,   20,   54,
};
final static short yydgoto[] = {                          8,
   35,   10,   11,   12,   37,   13,   14,   15,   16,   17,
   46,   26,   73,   74,   95,   80,   50,   18,   48,   27,
   28,   29,   60,
};
final static short yysindex[] = {                        75,
  -52,  -37,  -40,    0,    0,  -42,   52,    0,    0,   75,
   75, -243,  -30,  -27,  -14,  -12,  -34, -212,    0,    0,
    0,    0,    0, -217,   39, -198,   45,  -26,    0,   21,
 -211,   52,  -17,    0,    0,    0,  -21,    0,    0,    0,
    0,   84,    0,  -46, -197,    0, -189,    0,  -35,   94,
    0,    0,    0,    0,    0,    0,    0,    0,   45,   52,
    0,   52,   52,   52,   52,   52,    0,   35,  -17,    0,
 -190,    0, -180,    0,    0, -182,   75,    0,    0, -178,
    0, -184,    0,  -28,  -23,  -26,  -26,    3,    0,    0,
    0,    0,    0,    0,   84,    0,    0,    0,   18,    0,
    0,    0,    0,
};
final static short yyrindex[] = {                      -181,
    0,    0,    0,    0,    0,    0,    0,    0,    0, -181,
    1,    0,    0,    0,    0,    0, -181,    0,    0,    0,
    0,    0,    0,    0,    0,   62,    0,  -10,    0,    0,
    0,   27,   32,    0,    0,    0,    0,    0,    0,    0,
    0, -181,    0, -181,  -55,    0, -168,    0,    0, -181,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  -14,  -12,    0,
    0,    0,    0,    0,    0,    0,  104,    0,    0,    0,
    0,   92,    0,    0,    0,   12,   34,    0,    0,    0,
    0,    0,    0,    0, -181,    0,    0,    0,  -59,    0,
    0,    0,    0,
};
final static short yygindex[] = {                         0,
   14,    0,   -8,    0,    0,    0,    0,    0,    0,    0,
    0,   49,  -11,    7,    0,    0,    0,    0,    0,    5,
  -22,    2,  -16,
};
final static int YYTABLESIZE=370;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         31,
    4,   58,   25,   33,   25,   47,   19,   24,   45,   24,
   64,   33,   98,    9,    7,   65,   24,  100,   32,   62,
   66,   63,   71,   34,   36,   62,    7,   63,   38,   59,
   46,   39,   46,   45,   46,   77,   69,   70,   83,   86,
   87,   45,   84,  101,   40,   62,   41,   63,   46,   46,
   46,   46,   44,   49,   44,   51,   44,   76,  103,   61,
   62,   67,   63,   68,   85,   78,   89,   90,   88,   79,
   44,   44,   44,   44,   45,   91,   45,   92,   45,   93,
   94,   96,   97,   24,   27,   42,   45,   62,   99,   63,
   43,   21,   45,   45,   45,   45,   24,   82,   57,   56,
   58,  102,    0,    0,   57,   56,   58,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   17,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    7,    0,    0,    0,    0,
    0,    0,    0,    0,    7,    0,    0,    0,    0,    0,
    0,    0,   30,    0,    7,    0,    0,    0,    0,    0,
    0,    0,   37,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   58,   58,   58,
   58,   58,   58,   33,   33,    0,   58,   58,   58,    1,
    2,    0,    0,    0,    3,    0,   75,    4,    5,    0,
   20,    6,    2,    0,   42,   43,    3,   44,    0,    0,
   21,   81,   21,    6,   30,   22,   23,   22,   23,   21,
    0,    0,    0,    0,   22,   23,   46,   46,   46,   46,
   46,   46,    0,    0,    0,   46,   46,   46,   46,   46,
   46,   46,    0,    4,    0,    0,   27,    0,   44,   44,
   44,   44,   44,   44,    0,    0,    0,   44,   44,   44,
   44,   44,   44,   44,    0,    0,    0,    0,    0,    0,
   45,   45,   45,   45,   45,   45,    0,    0,    0,   45,
   45,   45,   45,   45,   45,   45,   21,   52,   53,   54,
   55,   22,   23,   52,   53,   54,   55,    0,   17,   21,
   17,   17,   17,   17,   22,   23,    0,   17,    0,   17,
    1,    2,    0,    0,    0,    3,    0,    0,    4,    5,
    2,    0,    6,   72,    3,   44,    0,    0,   30,    0,
    2,    6,   30,   30,    3,   44,    0,   30,    0,   30,
    0,    6,   37,   37,    0,    0,    4,    0,    0,   27,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
    0,   61,   40,   59,   40,   17,   59,   45,   17,   45,
   27,    7,   41,    0,   61,   42,   45,   41,   61,   43,
   47,   45,   44,   10,  268,   43,   61,   45,   59,   25,
   41,   59,   43,   42,   45,   44,   32,   59,   50,   62,
   63,   50,   59,   41,   59,   43,   59,   45,   59,   60,
   61,   62,   41,  266,   43,  273,   45,   44,   41,  258,
   43,   41,   45,  275,   60,  263,   65,   66,   64,  259,
   59,   60,   61,   62,   41,   41,   43,  268,   45,  260,
  263,  260,  267,   45,  266,   59,   95,   43,   84,   45,
   59,  260,   59,   60,   61,   62,   45,   49,   60,   61,
   62,   95,   -1,   -1,   60,   61,   62,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   61,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   61,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   61,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   61,   -1,   61,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   59,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,
  260,  261,  262,  259,  260,   -1,  266,  267,  268,  256,
  257,   -1,   -1,   -1,  261,   -1,  263,  264,  265,   -1,
  258,  268,  257,   -1,  259,  260,  261,  262,   -1,   -1,
  268,  267,  268,  268,  275,  273,  274,  273,  274,  268,
   -1,   -1,   -1,   -1,  273,  274,  257,  258,  259,  260,
  261,  262,   -1,   -1,   -1,  266,  267,  268,  269,  270,
  271,  272,   -1,  263,   -1,   -1,  266,   -1,  257,  258,
  259,  260,  261,  262,   -1,   -1,   -1,  266,  267,  268,
  269,  270,  271,  272,   -1,   -1,   -1,   -1,   -1,   -1,
  257,  258,  259,  260,  261,  262,   -1,   -1,   -1,  266,
  267,  268,  269,  270,  271,  272,  268,  269,  270,  271,
  272,  273,  274,  269,  270,  271,  272,   -1,  257,  268,
  259,  260,  261,  262,  273,  274,   -1,  266,   -1,  268,
  256,  257,   -1,   -1,   -1,  261,   -1,   -1,  264,  265,
  257,   -1,  268,  260,  261,  262,   -1,   -1,  257,   -1,
  257,  268,  261,  262,  261,  262,   -1,  266,   -1,  268,
   -1,  268,  259,  260,   -1,   -1,  263,   -1,   -1,  266,
};
}
final static short YYFINAL=8;
final static short YYMAXTOKEN=275;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'","'*'","'+'","','",
"'-'",null,"'/'",null,null,null,null,null,null,null,null,null,null,null,"';'",
"'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,"IF","THEN","ELSE","ENDIF","PRINT","BEGIN","END",
"INT","ULONG","WHILE","DO","IDENTIFICADOR","MENOROIGUAL","DISTINTO","IGUAL",
"MAYOROIGUAL","ENTERO","ENTERO_LARGO","CADENA",
};
final static String yyrule[] = {
"$accept : programa",
"programa : codigo",
"codigo : declaracion codigo",
"codigo : sentencias codigo",
"codigo : sentencias",
"declaracion : tipo identificadores ';'",
"declaracion : error ';'",
"tipo : INT",
"tipo : ULONG",
"identificadores : IDENTIFICADOR",
"identificadores : identificadores ',' IDENTIFICADOR",
"sentencias : seleccion ';'",
"sentencias : mientras ';'",
"sentencias : imprimir ';'",
"sentencias : asignacion ';'",
"seleccion : ifencabezado cuerpoif",
"ifencabezado : IF condicion THEN",
"ifencabezado : IF condicion",
"ifencabezado : IF THEN",
"$$1 :",
"cuerpoif : ejecucion ELSE $$1 falsoif",
"$$2 :",
"cuerpoif : ejecucion $$2 ENDIF",
"cuerpoif : ELSE falsoif",
"cuerpoif : ENDIF",
"falsoif : ejecucion ENDIF",
"falsoif : ENDIF",
"$$3 :",
"mientras : $$3 mientrasencabezado ejecucion",
"mientrasencabezado : WHILE condicion DO",
"mientrasencabezado : WHILE condicion",
"mientrasencabezado : WHILE DO",
"ejecucion : bloque",
"ejecucion : sentencias",
"bloque : BEGIN codigo END",
"bloque : sentencias END",
"bloque : BEGIN END",
"bloque : BEGIN sentencias",
"imprimir : PRINT '(' CADENA ')'",
"imprimir : PRINT CADENA ')'",
"imprimir : PRINT '(' CADENA",
"asignacion : IDENTIFICADOR '=' expresion",
"asignacion : IDENTIFICADOR '='",
"asignacion : '=' expresion",
"expresion : expresion '+' termino",
"expresion : expresion '-' termino",
"expresion : termino",
"termino : termino '*' factor",
"termino : termino '/' factor",
"termino : factor",
"factor : IDENTIFICADOR",
"factor : ENTERO",
"factor : '-' ENTERO",
"factor : ENTERO_LARGO",
"condicion : '(' expresion comparador expresion ')'",
"condicion : expresion comparador expresion ')'",
"condicion : '(' comparador expresion ')'",
"condicion : '(' expresion comparador ')'",
"condicion : '(' expresion comparador expresion",
"comparador : MAYOROIGUAL",
"comparador : MENOROIGUAL",
"comparador : DISTINTO",
"comparador : '<'",
"comparador : '>'",
"comparador : IGUAL",
"comparador : '='",
};

//#line 140 "GramaticaTP3.Y"

static Lexico lexico;
static Tercetos tercetos;
static Errores errores;
public int nt;
public String tipoOp;
public Map<String, ValoresTS> TSMap;
private Stack<Integer> pilaTerceto;	
public String tipoVariable;					  

//Constructor
public Parser(Lexico analizadorLexico, Tercetos tercetos, Errores errores, Map<String, ValoresTS> tablaSimbolosMap)
{
	lexico = analizadorLexico;
	Parser.tercetos = tercetos;
	Parser.errores = errores;
	this.pilaTerceto = new Stack<Integer>();
	TSMap = tablaSimbolosMap;
}
private void yyerror(String string) {
	//no se si lo usaremos
}

//Recupera tokens de Lexico
private int yylex() {
	int tokenNro;
	
	tokenNro = 0;
	if (!lexico.isFinArchivo()) {
		tokenNro = lexico.getToken();
		yylval = new ParserVal(lexico.getyylval());
	}
	
	return tokenNro;
}
// verifica si esta declarada la variable, de ser asi da error sino la declara
public void setVariable(String variable,String tipo, int linea) {
	if (TSMap.containsKey(variable)) { // si existe me fijo si esta declarada
		ValoresTS valores = TSMap.get(variable); // recupero los valores
		if (valores.getTokenTipo() == null) {//no declarada
			ValoresTS v = new ValoresTS();
			v.setTokenID(0); 
			v.setTokenTipo(tipo);
			TSMap.put(variable, v);
		}else {
			errores.creaError(linea, "Variable ya declarada");
		}
	}
}

public String verificaTipos(String operador1, String operador2, int linea) {
	String tipoOp1 = "";
	String tipoOp2 = "";
	String tipoSalida = null;
	
	//recupero tipo del operador 1
	if (TSMap.containsKey(operador1)) { // si existe me fijo si esta declarada
		ValoresTS valores = TSMap.get(operador1); // recupero los valores
		if (valores.getTokenTipo() != null) {	//declarada
			tipoOp1 = valores.getTokenTipo();	//recupero el tipo de la variable
		}else {
			errores.creaError(linea, "Variable " +operador1+" no declarada");
		}
	}else {
		if ('[' == operador1.charAt(0)) { 
			String ter = operador1.substring(1,operador1.length()-1);
			String[] tercetoAux = tercetos.getTerceto(Integer.parseInt(ter)-1);
			tipoOp1 = tercetoAux[3];
		}
	}
	
	//recupero tipo del operador 2
	if (TSMap.containsKey(operador2)) { // si existe me fijo si esta declarada
		ValoresTS valores = TSMap.get(operador2); // recupero los valores
		if (valores.getTokenTipo() != null) {//declarada
			tipoOp2 = valores.getTokenTipo();	//recupero el tipo de la variable
		}else {
			errores.creaError(linea, "Variable " +operador2+" no declarada");
		}
	}else {
		if ('[' == operador2.charAt(0)) { 
			String ter = operador2.substring(1,operador2.length()-1);
			String[] tercetoAux = tercetos.getTerceto(Integer.parseInt(ter)-1);
			tipoOp2 = tercetoAux[3];
		}
	}
	
	if (tipoOp1 != "" && tipoOp2 != "") {
		if (tipoOp1 != tipoOp2 ) { // Si son distintos emito error
			errores.creaError(linea, "Variables de tipos incompatible ("+tipoOp1+" <> "+tipoOp2+ " )" );
		}else {
			tipoSalida = tipoOp1;
		}
	}
	return tipoSalida;
}
//#line 454 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 6:
//#line 20 "GramaticaTP3.Y"
{errores.creaError(lexico.getCantLineas(),"error en declaracion de identificador");}
break;
case 7:
//#line 23 "GramaticaTP3.Y"
{tipoVariable = "INT";}
break;
case 8:
//#line 24 "GramaticaTP3.Y"
{tipoVariable = "ULONG";}
break;
case 9:
//#line 27 "GramaticaTP3.Y"
{this.setVariable(val_peek(0).sval,tipoVariable, lexico.getCantLineas());}
break;
case 10:
//#line 28 "GramaticaTP3.Y"
{this.setVariable(val_peek(0).sval,tipoVariable, lexico.getCantLineas());}
break;
case 16:
//#line 40 "GramaticaTP3.Y"
{nt=tercetos.creaTerceto("BF",val_peek(1).sval,"-",""); 	
																					 yyval.sval = "["+nt+"]"; 
																					 this.pilaTerceto.push(nt);}
break;
case 17:
//#line 43 "GramaticaTP3.Y"
{errores.creaError(lexico.getCantLineas(),"se espera THEN");}
break;
case 18:
//#line 44 "GramaticaTP3.Y"
{errores.creaError(lexico.getCantLineas(),"se espera condicion para evaluar");}
break;
case 19:
//#line 47 "GramaticaTP3.Y"
{nt=tercetos.creaTerceto("BI","-","-",""); 
																					 yyval.sval = "["+nt+"]"; 
																					 tercetos.completaTerceto(this.pilaTerceto.pop(),nt+1);
																					 tercetos.creaTerceto("label"+Integer.toString(nt+1),"-","-","");
																					 this.pilaTerceto.push(nt);}
break;
case 21:
//#line 52 "GramaticaTP3.Y"
{tercetos.completaTerceto(this.pilaTerceto.pop(),nt+1);}
break;
case 23:
//#line 53 "GramaticaTP3.Y"
{errores.creaError(lexico.getCantLineas(),"se espera ejecucion por verdadero");}
break;
case 24:
//#line 54 "GramaticaTP3.Y"
{errores.creaError(lexico.getCantLineas(),"if vacio");}
break;
case 25:
//#line 58 "GramaticaTP3.Y"
{tercetos.completaTerceto((int)this.pilaTerceto.pop(),nt+1);tercetos.creaTerceto("label"+Integer.toString(nt+1),"-","-","");}
break;
case 26:
//#line 59 "GramaticaTP3.Y"
{errores.creaError(lexico.getCantLineas(),"se espera ejecucion por falso");}
break;
case 27:
//#line 62 "GramaticaTP3.Y"
{this.pilaTerceto.push(tercetos.getCantidadTercetos()+1);}
break;
case 28:
//#line 62 "GramaticaTP3.Y"
{yyval.sval = "["+nt+"]"; 
																													 tercetos.completaTerceto(this.pilaTerceto.pop(),nt+2); 
																													 nt=tercetos.creaTerceto("BI", "["+Integer.toString(this.pilaTerceto.pop())+"]","-","");tercetos.creaTerceto("label"+Integer.toString(nt+1),"-","-","");}
break;
case 29:
//#line 67 "GramaticaTP3.Y"
{nt=tercetos.creaTerceto("BF",val_peek(1).sval,"-",""); 	
																					 yyval.sval = "["+nt+"]"; 
																					 this.pilaTerceto.push(nt);tercetos.creaTerceto("label"+Integer.toString(nt),"-","-","");}
break;
case 30:
//#line 70 "GramaticaTP3.Y"
{errores.creaError(lexico.getCantLineas(),"se espera DO");}
break;
case 31:
//#line 71 "GramaticaTP3.Y"
{errores.creaError(lexico.getCantLineas(),"se espera condicion para evaluar");}
break;
case 35:
//#line 79 "GramaticaTP3.Y"
{errores.creaError(lexico.getCantLineas(),"se espera BEGIN");}
break;
case 36:
//#line 80 "GramaticaTP3.Y"
{errores.creaError(lexico.getCantLineas(),"se espera alguna sentencia al menos");}
break;
case 37:
//#line 81 "GramaticaTP3.Y"
{errores.creaError(lexico.getCantLineas(),"se espera END");}
break;
case 38:
//#line 84 "GramaticaTP3.Y"
{nt=tercetos.creaTerceto("PRINTF",val_peek(1).sval.replaceAll("\\s"," ").trim(),"-","");}
break;
case 39:
//#line 85 "GramaticaTP3.Y"
{errores.creaError(lexico.getCantLineas()," se espera ( ");}
break;
case 40:
//#line 86 "GramaticaTP3.Y"
{errores.creaError(lexico.getCantLineas()," se espera ) ");}
break;
case 41:
//#line 89 "GramaticaTP3.Y"
{tipoOp = verificaTipos(val_peek(2).sval, val_peek(0).sval, lexico.getCantLineas());
																					 nt=tercetos.creaTerceto(":=",val_peek(2).sval,val_peek(0).sval,tipoOp);
																					 yyval.sval = "["+nt+"]";}
break;
case 42:
//#line 92 "GramaticaTP3.Y"
{errores.creaError(lexico.getCantLineas(),"se desconoce lo que se le asignará luego del =");}
break;
case 43:
//#line 93 "GramaticaTP3.Y"
{errores.creaError(lexico.getCantLineas(),"se desconoce la variable a la que se le asignará la linea");}
break;
case 44:
//#line 96 "GramaticaTP3.Y"
{tipoOp = verificaTipos(val_peek(2).sval,val_peek(0).sval,lexico.getCantLineas()); 
																					 nt=tercetos.creaTerceto(val_peek(1).sval,val_peek(2).sval,val_peek(0).sval,tipoOp); 
																					 yyval.sval = "["+nt+"]";}
break;
case 45:
//#line 99 "GramaticaTP3.Y"
{tipoOp = verificaTipos(val_peek(2).sval,val_peek(0).sval,lexico.getCantLineas()); 
																					 nt=tercetos.creaTerceto(val_peek(1).sval,val_peek(2).sval,val_peek(0).sval,tipoOp); 
																					 yyval.sval = "["+nt+"]";}
break;
case 46:
//#line 102 "GramaticaTP3.Y"
{yyval.sval=val_peek(0).sval;}
break;
case 47:
//#line 105 "GramaticaTP3.Y"
{tipoOp = verificaTipos(val_peek(2).sval, val_peek(0).sval, lexico.getCantLineas()); 
																					 nt=tercetos.creaTerceto(val_peek(1).sval,val_peek(2).sval,val_peek(0).sval,tipoOp); 
																					 yyval.sval = "["+nt+"]";}
break;
case 48:
//#line 108 "GramaticaTP3.Y"
{tipoOp = verificaTipos(val_peek(2).sval, val_peek(0).sval, lexico.getCantLineas()); 
																					 nt=tercetos.creaTerceto(val_peek(1).sval,val_peek(2).sval,val_peek(0).sval,tipoOp); 
																					 yyval.sval = "["+nt+"]";}
break;
case 49:
//#line 111 "GramaticaTP3.Y"
{yyval.sval=val_peek(0).sval;}
break;
case 50:
//#line 114 "GramaticaTP3.Y"
{yyval.sval=val_peek(0).sval;}
break;
case 51:
//#line 115 "GramaticaTP3.Y"
{yyval.sval=val_peek(0).sval;}
break;
case 52:
//#line 116 "GramaticaTP3.Y"
{nt=tercetos.creaTerceto("*",val_peek(0).sval,"-1_i",""); 
																					 yyval.sval = "["+nt+"]";}
break;
case 53:
//#line 118 "GramaticaTP3.Y"
{yyval.sval=val_peek(0).sval;}
break;
case 54:
//#line 121 "GramaticaTP3.Y"
{tipoOp = verificaTipos(val_peek(3).sval, val_peek(1).sval, lexico.getCantLineas());  
																					 nt=tercetos.creaTerceto(val_peek(2).sval,val_peek(3).sval,val_peek(1).sval,""); 
																					 yyval.sval = "["+nt+"]";
																					}
break;
case 55:
//#line 125 "GramaticaTP3.Y"
{errores.creaError(lexico.getCantLineas(),"se espera (");}
break;
case 56:
//#line 126 "GramaticaTP3.Y"
{errores.creaError(lexico.getCantLineas(),"se espera operando");}
break;
case 57:
//#line 127 "GramaticaTP3.Y"
{errores.creaError(lexico.getCantLineas(),"se espera operando para evaluar la condicion");}
break;
case 58:
//#line 128 "GramaticaTP3.Y"
{errores.creaError(lexico.getCantLineas(),"se espera )");}
break;
case 59:
//#line 131 "GramaticaTP3.Y"
{yyval.sval=val_peek(0).sval;}
break;
case 60:
//#line 132 "GramaticaTP3.Y"
{yyval.sval=val_peek(0).sval;}
break;
case 61:
//#line 133 "GramaticaTP3.Y"
{yyval.sval=val_peek(0).sval;}
break;
case 62:
//#line 134 "GramaticaTP3.Y"
{yyval.sval=val_peek(0).sval;}
break;
case 63:
//#line 135 "GramaticaTP3.Y"
{yyval.sval=val_peek(0).sval;}
break;
case 64:
//#line 136 "GramaticaTP3.Y"
{yyval.sval=val_peek(0).sval;}
break;
case 65:
//#line 137 "GramaticaTP3.Y"
{errores.creaError(lexico.getCantLineas(),"se espera = para realizar comparación");}
break;
//#line 827 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
