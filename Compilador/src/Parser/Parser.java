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






//#line 2 "g0711.Y"
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
    7,    3,    9,    3,   11,    3,    3,    6,   13,   13,
   13,   18,   14,   19,   14,   14,   14,   17,   17,   21,
    8,   20,   20,   20,   16,   16,   22,   22,   22,   22,
   10,   10,   10,   12,   12,   12,   23,   23,   23,   24,
   24,   24,   25,   25,   25,   25,   15,   15,   15,   15,
   15,   26,   26,   26,   26,   26,   26,   26,
};
final static short yylen[] = {                            2,
    1,    2,    2,    1,    3,    2,    1,    1,    1,    3,
    0,    3,    0,    3,    0,    3,    2,    2,    3,    2,
    2,    0,    4,    0,    3,    2,    1,    2,    1,    0,
    3,    3,    2,    2,    1,    1,    3,    2,    2,    2,
    4,    3,    3,    3,    2,    2,    3,    3,    1,    3,
    3,    1,    1,    1,    2,    1,    5,    4,    4,    4,
    4,    1,    1,    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    7,    8,    0,    0,    0,    1,    0,    0,    0,
    0,   30,    0,    0,    6,    0,   53,   54,   56,    0,
    0,    0,   52,    2,    3,    9,    0,    0,    0,    0,
    0,    0,    0,    0,   17,    0,   55,    0,    0,    0,
    0,    5,    0,   21,    0,    0,    0,   12,    0,   27,
    0,    0,   18,    0,   35,   14,    0,    0,    0,    0,
   16,    0,    0,   50,   51,   10,   63,   64,   67,   62,
   68,   65,   66,    0,    0,   19,    0,   29,    0,   26,
   39,    0,    0,   38,   22,    0,   34,    0,   31,   42,
    0,    0,    0,    0,   28,   37,    0,   25,   32,   41,
   60,    0,   59,   58,   23,   57,
};
final static short yydgoto[] = {                          6,
   25,    8,    9,   10,   27,   29,   11,   31,   12,   34,
   13,   14,   30,   53,   46,   79,   80,   97,   86,   58,
   32,   55,   47,   22,   23,   75,
};
final static short yysindex[] = {                        20,
  -36,    0,    0,  -17,   -8,    0,    0,   20,   20, -242,
 -219,    0, -211,   -4,    0,   -8,    0,    0,    0, -205,
  -12,  -22,    0,    0,    0,    0,  -25,  -37,   16,   83,
   23, -189,  -40,   25,    0,  -12,    0,   -8,   -8,   -8,
   -8,    0, -188,    0,  -21, -179,  -15,    0,  -50,    0,
  -39, -180,    0, -174,    0,    0,  -35,  -34,   45, -186,
    0,  -22,  -22,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  -15,   -8,    0,   -8,    0, -173,    0,
    0, -172,   20,    0,    0, -163,    0, -166,    0,    0,
   61,  -28,   18,   21,    0,    0,  -50,    0,    0,    0,
    0,   24,    0,    0,    0,    0,
};
final static short yyrindex[] = {                      -245,
    0,    0,    0,    0,    0,    0,    0, -245,    1,    0,
    0,    0,    0,    0,    0,   44,    0,    0,    0,    0,
   47,   11,    0,    0,    0,    0,    0,    0,    0, -245,
    0,    0,    0,    0,    0,   48,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -46,    0,    0, -245,    0,
 -245,  -55,    0, -156,    0,    0,    0, -245,    0,    0,
    0,   33,   55,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   71,    0,    0,    0,    0,   78,    0,    0,
   49,    0,    0,    0,    0,    0, -245,    0,    0,    0,
    0,  -59,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    6,    0,    2,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   52,  -23,   14,    0,    0,    0,
    0,    0,   13,    4,    8,  -38,
};
final static int YYTABLESIZE=351;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         60,
    4,   61,   45,   36,   45,    7,   54,   20,   77,   20,
    5,   11,  101,   24,   20,   15,   20,   21,   43,   40,
   13,    5,   15,   20,   41,   26,    5,   38,   36,   39,
   38,   52,   39,   42,   89,   92,   20,   28,   72,   71,
   73,   62,   63,   16,   72,   71,   73,   64,   65,   33,
   52,   49,   83,   49,   35,   49,   82,   74,  103,   52,
   38,  104,   39,   38,  106,   39,   38,   37,   39,   49,
   49,   49,   49,   47,   48,   47,   57,   47,   76,   66,
    5,   56,   84,   61,   85,   90,   95,   93,   91,   94,
   96,   47,   47,   47,   47,   48,   98,   48,   52,   48,
   99,  100,   45,   24,  102,   46,   44,   43,   88,    0,
  105,    0,    0,   48,   48,   48,   48,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   40,
    0,    0,    0,    0,    0,    0,    0,    0,   33,    0,
    0,    0,    0,    5,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   61,   61,   61,
   61,   61,   61,   36,   36,    0,   61,   61,   61,   78,
   20,   51,   20,   20,   20,   20,    1,    4,    0,   20,
   44,   20,    0,   81,    2,    3,    0,   51,    4,    0,
   17,   87,   17,    4,   59,   18,   19,   18,   19,   17,
    0,    0,    0,    0,   18,   19,   17,   67,   68,   69,
   70,   18,   19,   67,   68,   69,   70,   11,    0,   17,
    0,   15,    0,    4,   18,   19,   13,   49,   49,   49,
   49,   49,   49,    0,    0,    1,   49,   49,   49,   49,
   49,   49,   49,    2,    3,    0,    0,    4,    0,   47,
   47,   47,   47,   47,   47,    0,    0,    0,   47,   47,
   47,   47,   47,   47,   47,    0,    0,    0,    0,    0,
    0,   48,   48,   48,   48,   48,   48,    0,    0,    0,
   48,   48,   48,   48,   48,   48,   48,   11,    0,   40,
   40,   15,    0,    4,   33,    0,   13,    0,   33,   33,
    0,   49,   50,   33,   51,   33,    0,    0,    0,    0,
    4,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
    0,   61,   40,   59,   40,    0,   30,   45,   47,   45,
   61,  257,   41,    8,   61,  261,   45,    5,   44,   42,
  266,   61,   59,   45,   47,  268,   61,   43,   16,   45,
   43,   30,   45,   59,   58,   74,   45,  257,   60,   61,
   62,   38,   39,   61,   60,   61,   62,   40,   41,  261,
   49,   41,   51,   43,   59,   45,   51,   45,   41,   58,
   43,   41,   45,   43,   41,   45,   43,  273,   45,   59,
   60,   61,   62,   41,   59,   43,  266,   45,  258,  268,
   61,   59,  263,   59,  259,   41,  260,   75,  275,   77,
  263,   59,   60,   61,   62,   41,  260,   43,   97,   45,
  267,   41,   59,  260,   92,   59,   59,   59,   57,   -1,
   97,   -1,   -1,   59,   60,   61,   62,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   59,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   61,   -1,
   -1,   -1,   -1,   61,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,
  260,  261,  262,  259,  260,   -1,  266,  267,  268,  260,
  257,  262,  259,  260,  261,  262,  256,  268,   -1,  266,
  258,  268,   -1,  263,  264,  265,   -1,  262,  268,   -1,
  268,  267,  268,  268,  275,  273,  274,  273,  274,  268,
   -1,   -1,   -1,   -1,  273,  274,  268,  269,  270,  271,
  272,  273,  274,  269,  270,  271,  272,  257,   -1,  268,
   -1,  261,   -1,  263,  273,  274,  266,  257,  258,  259,
  260,  261,  262,   -1,   -1,  256,  266,  267,  268,  269,
  270,  271,  272,  264,  265,   -1,   -1,  268,   -1,  257,
  258,  259,  260,  261,  262,   -1,   -1,   -1,  266,  267,
  268,  269,  270,  271,  272,   -1,   -1,   -1,   -1,   -1,
   -1,  257,  258,  259,  260,  261,  262,   -1,   -1,   -1,
  266,  267,  268,  269,  270,  271,  272,  257,   -1,  259,
  260,  261,   -1,  263,  257,   -1,  266,   -1,  261,  262,
   -1,  259,  260,  266,  262,  268,   -1,   -1,   -1,   -1,
  268,
};
}
final static short YYFINAL=6;
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
"$$1 :",
"sentencias : $$1 seleccion ';'",
"$$2 :",
"sentencias : $$2 mientras ';'",
"$$3 :",
"sentencias : $$3 imprimir ';'",
"sentencias : asignacion ';'",
"seleccion : ifencabezado cuerpoif",
"ifencabezado : IF condicion THEN",
"ifencabezado : IF condicion",
"ifencabezado : IF THEN",
"$$4 :",
"cuerpoif : ejecucion ELSE $$4 falsoif",
"$$5 :",
"cuerpoif : ejecucion $$5 ENDIF",
"cuerpoif : ELSE falsoif",
"cuerpoif : ENDIF",
"falsoif : ejecucion ENDIF",
"falsoif : ENDIF",
"$$6 :",
"mientras : $$6 mientrasencabezado ejecucion",
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

//#line 134 "g0711.Y"

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
			System.out.println("terceto a donde tengo que ir " +ter);
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
			System.out.println("terceto a donde tengo que ir " +ter);
			String[] tercetoAux = tercetos.getTerceto(Integer.parseInt(ter)-1);
			tipoOp2 = tercetoAux[3];
		}
	}
	
	if (tipoOp1 != "" && tipoOp2 != "") {
		if (tipoOp1 != tipoOp2 ) { // Si son distintos emito error
			errores.creaError(linea, "Variables de tipo incompatible ("+tipoOp1+" <> "+tipoOp2+ " )" );
		}else {
			tipoSalida = tipoOp1;
		}
	}
	return tipoSalida;
}
//#line 457 "Parser.java"
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
//#line 20 "g0711.Y"
{System.out.println("Linea " + lexico.getCantLineas() + ": error en declaracion de identificador"); errores.creaError(lexico.getCantLineas(),"error en declaracion de identificador");}
break;
case 7:
//#line 23 "g0711.Y"
{tipoVariable = "INT"; System.out.println("Linea " + lexico.getCantLineas()+ ": declaracion de tipo INT");}
break;
case 8:
//#line 24 "g0711.Y"
{tipoVariable = "ULONG"; System.out.println("Linea " + lexico.getCantLineas()+ ": declaracion de tipo ULONG");}
break;
case 9:
//#line 27 "g0711.Y"
{System.out.println("Linea " + lexico.getCantLineas() + "identificador solo"); this.setVariable(val_peek(0).sval,tipoVariable, lexico.getCantLineas());}
break;
case 10:
//#line 28 "g0711.Y"
{System.out.println("Linea " + lexico.getCantLineas()+ ": declaracion de identificador multiple: "+lexico.getyylval()); this.setVariable(val_peek(0).sval,tipoVariable, lexico.getCantLineas());}
break;
case 11:
//#line 31 "g0711.Y"
{System.out.println("Linea " + lexico.getCantLineas()+ ": inicio de IF");}
break;
case 13:
//#line 32 "g0711.Y"
{System.out.println("Linea " + lexico.getCantLineas()+ ": inicio de WHILE");}
break;
case 15:
//#line 33 "g0711.Y"
{System.out.println("Linea " + lexico.getCantLineas()+ ": sentencia PRINT");}
break;
case 19:
//#line 40 "g0711.Y"
{System.out.println("Linea " + lexico.getCantLineas()+ ": encabezado de IF"); 
																					nt=tercetos.creaTerceto("BF",val_peek(1).sval,"-",""); 	
																					yyval.sval = "["+nt+"]"; 
																					this.pilaTerceto.push(nt);
																					/*System.out.println("Terceto pila (+2): " + this.pilaTerceto.pop().toString());*/
																					}
break;
case 20:
//#line 46 "g0711.Y"
{System.out.println("Linea " + lexico.getCantLineas()+ ": ERROR - se espera THEN"); errores.creaError(lexico.getCantLineas(),"se espera THEN");}
break;
case 21:
//#line 47 "g0711.Y"
{System.out.println("Linea " + lexico.getCantLineas()+ ": ERROR - se espera condicion para evaluar"); errores.creaError(lexico.getCantLineas(),"se espera condicion para evaluar");}
break;
case 22:
//#line 50 "g0711.Y"
{System.out.println("Linea " + lexico.getCantLineas()+ ": ELSE"); 
																					nt=tercetos.creaTerceto("BI","-","-",""); 
																					yyval.sval = "["+nt+"]"; 
																					tercetos.completaTerceto(this.pilaTerceto.pop(),nt+1); 
																					this.pilaTerceto.push(nt);}
break;
case 24:
//#line 55 "g0711.Y"
{tercetos.completaTerceto(this.pilaTerceto.pop(),nt+1);}
break;
case 25:
//#line 55 "g0711.Y"
{System.out.println("Linea " + lexico.getCantLineas()+ ": fin if simple");}
break;
case 26:
//#line 56 "g0711.Y"
{System.out.println("Linea " + lexico.getCantLineas()+ ": ERROR - se espera ejecucion por verdadero"); errores.creaError(lexico.getCantLineas(),"se espera ejecucion por verdadero");}
break;
case 27:
//#line 57 "g0711.Y"
{System.out.println("Linea " + lexico.getCantLineas()+ ": ERROR - if vacio"); errores.creaError(lexico.getCantLineas(),"if vacio");}
break;
case 28:
//#line 61 "g0711.Y"
{System.out.println("Linea " + lexico.getCantLineas()+ ": fin if doble"); tercetos.completaTerceto((int)this.pilaTerceto.pop(),nt+1);}
break;
case 29:
//#line 62 "g0711.Y"
{System.out.println("Linea " + lexico.getCantLineas()+ ": ERROR - se espera ejecucion por falso"); errores.creaError(lexico.getCantLineas(),"se espera ejecucion por falso");}
break;
case 30:
//#line 65 "g0711.Y"
{this.pilaTerceto.push(tercetos.getCantidadTercetos()+1);}
break;
case 31:
//#line 65 "g0711.Y"
{System.out.println("Linea " + lexico.getCantLineas()+ ": finaliza un ciclo WHILE");
																					yyval.sval = "["+nt+"]"; 
																					tercetos.completaTerceto(this.pilaTerceto.pop(),nt+2); 
																					nt=tercetos.creaTerceto("BI", "["+Integer.toString(this.pilaTerceto.pop())+"]","-","");}
break;
case 32:
//#line 71 "g0711.Y"
{System.out.println("Linea " + lexico.getCantLineas()+ ": encabezado de WHILE");
																					nt=tercetos.creaTerceto("BF",val_peek(1).sval,"-",""); 	
																					yyval.sval = "["+nt+"]"; 
																					this.pilaTerceto.push(nt);}
break;
case 33:
//#line 75 "g0711.Y"
{System.out.println("Linea " + lexico.getCantLineas()+ ": ERROR - se espera DO"); errores.creaError(lexico.getCantLineas(),"se espera DO");}
break;
case 34:
//#line 76 "g0711.Y"
{System.out.println("Linea " + lexico.getCantLineas()+ ": ERROR - se espera condicion para evaluar"); errores.creaError(lexico.getCantLineas(),"se espera condicion para evaluar");}
break;
case 38:
//#line 84 "g0711.Y"
{System.out.println("Linea " + lexico.getCantLineas()+ ": ERROR - se espera BEGIN"); errores.creaError(lexico.getCantLineas(),"se espera BEGIN");}
break;
case 39:
//#line 85 "g0711.Y"
{System.out.println("Linea " + lexico.getCantLineas()+ ": ERROR - se espera alguna sentencia al menos en la linea"); errores.creaError(lexico.getCantLineas(),"se espera alguna sentencia al menos");}
break;
case 40:
//#line 86 "g0711.Y"
{System.out.println("Linea " + lexico.getCantLineas()+ ": ERROR - se espera END"); errores.creaError(lexico.getCantLineas(),"se espera END");}
break;
case 41:
//#line 89 "g0711.Y"
{nt=tercetos.creaTerceto("PRINTF",val_peek(1).sval.replaceAll("\\s"," ").trim(),"-","");}
break;
case 42:
//#line 90 "g0711.Y"
{System.out.println("Linea " + lexico.getCantLineas()+ ": ERROR - se espera ("); errores.creaError(lexico.getCantLineas()," se espera ( ");}
break;
case 43:
//#line 91 "g0711.Y"
{System.out.println("Linea " + lexico.getCantLineas()+ ": ERROR - se espera )"); errores.creaError(lexico.getCantLineas()," se espera ) ");}
break;
case 44:
//#line 94 "g0711.Y"
{System.out.println(lexico.getCantLineas() +" (:=,"+val_peek(2).sval+","+val_peek(0).sval+")"); nt=tercetos.creaTerceto(":=",val_peek(2).sval,val_peek(0).sval,"");}
break;
case 45:
//#line 95 "g0711.Y"
{System.out.println("Linea " + lexico.getCantLineas()+ ": ERROR - se desconoce lo que se le asignará luego del ="); errores.creaError(lexico.getCantLineas(),"se desconoce lo que se le asignará luego del =");}
break;
case 46:
//#line 96 "g0711.Y"
{System.out.println("Linea " + lexico.getCantLineas()+ ": ERROR - se desconoce la variable a la que se le asignará la linea"); errores.creaError(lexico.getCantLineas(),"se desconoce la variable a la que se le asignará la linea");}
break;
case 47:
//#line 99 "g0711.Y"
{System.out.println(lexico.getCantLineas() +" ("+val_peek(1).sval+","+val_peek(2).sval+","+val_peek(0).sval+")"); tipoOp = verificaTipos(val_peek(2).sval,val_peek(0).sval,lexico.getCantLineas()); nt=tercetos.creaTerceto(val_peek(1).sval,val_peek(2).sval,val_peek(0).sval,tipoOp); yyval.sval = "["+nt+"]";}
break;
case 48:
//#line 100 "g0711.Y"
{System.out.println(lexico.getCantLineas() +" ("+val_peek(1).sval+","+val_peek(2).sval+","+val_peek(0).sval+")"); tipoOp = verificaTipos(val_peek(2).sval,val_peek(0).sval,lexico.getCantLineas()); nt=tercetos.creaTerceto(val_peek(1).sval,val_peek(2).sval,val_peek(0).sval,tipoOp); yyval.sval = "["+nt+"]";}
break;
case 49:
//#line 101 "g0711.Y"
{yyval.sval=val_peek(0).sval;}
break;
case 50:
//#line 104 "g0711.Y"
{System.out.println(lexico.getCantLineas() +" ("+val_peek(1).sval+","+val_peek(2).sval+","+val_peek(0).sval+")"); tipoOp = verificaTipos(val_peek(2).sval, val_peek(0).sval, lexico.getCantLineas()); nt=tercetos.creaTerceto(val_peek(1).sval,val_peek(2).sval,val_peek(0).sval,tipoOp); yyval.sval = "["+nt+"]";}
break;
case 51:
//#line 105 "g0711.Y"
{System.out.println(lexico.getCantLineas() +" ("+val_peek(1).sval+","+val_peek(2).sval+","+val_peek(0).sval+")"); tipoOp = verificaTipos(val_peek(2).sval, val_peek(0).sval, lexico.getCantLineas()); nt=tercetos.creaTerceto(val_peek(1).sval,val_peek(2).sval,val_peek(0).sval,tipoOp); yyval.sval = "["+nt+"]";}
break;
case 52:
//#line 106 "g0711.Y"
{yyval.sval=val_peek(0).sval;}
break;
case 53:
//#line 109 "g0711.Y"
{yyval.sval=val_peek(0).sval;}
break;
case 54:
//#line 110 "g0711.Y"
{yyval.sval=val_peek(0).sval;}
break;
case 55:
//#line 111 "g0711.Y"
{nt=tercetos.creaTerceto("*",val_peek(0).sval,"-1",""); yyval.sval = "["+nt+"]";}
break;
case 56:
//#line 112 "g0711.Y"
{yyval.sval=val_peek(0).sval;}
break;
case 57:
//#line 115 "g0711.Y"
{System.out.println(lexico.getCantLineas()+" ("+val_peek(2).sval+","+val_peek(3).sval+","+val_peek(1).sval+")"); 
																					nt=tercetos.creaTerceto(val_peek(2).sval,val_peek(3).sval,val_peek(1).sval,""); 
																					yyval.sval = "["+nt+"]";
																					}
break;
case 58:
//#line 119 "g0711.Y"
{System.out.println("Linea " + lexico.getCantLineas()+ ": ERROR - se espera ("); errores.creaError(lexico.getCantLineas(),"se espera (");}
break;
case 59:
//#line 120 "g0711.Y"
{System.out.println("Linea " + lexico.getCantLineas()+ ": ERROR - se espera operando"); errores.creaError(lexico.getCantLineas(),"se espera operando");}
break;
case 60:
//#line 121 "g0711.Y"
{System.out.println("Linea " + lexico.getCantLineas()+ ": ERROR - se espera operando para evaluar la condicion"); errores.creaError(lexico.getCantLineas(),"se espera operando para evaluar la condicion");}
break;
case 61:
//#line 122 "g0711.Y"
{System.out.println("Linea " + lexico.getCantLineas()+ ": ERROR - se espera )"); errores.creaError(lexico.getCantLineas(),"se espera )");}
break;
case 62:
//#line 125 "g0711.Y"
{yyval.sval=val_peek(0).sval;}
break;
case 63:
//#line 126 "g0711.Y"
{yyval.sval=val_peek(0).sval;}
break;
case 64:
//#line 127 "g0711.Y"
{yyval.sval=val_peek(0).sval;}
break;
case 65:
//#line 128 "g0711.Y"
{yyval.sval=val_peek(0).sval;}
break;
case 66:
//#line 129 "g0711.Y"
{yyval.sval=val_peek(0).sval;}
break;
case 67:
//#line 130 "g0711.Y"
{yyval.sval=val_peek(0).sval;}
break;
case 68:
//#line 131 "g0711.Y"
{System.out.println("Linea " + lexico.getCantLineas()+ ": ERROR - se espera = para realizar comparación"); errores.creaError(lexico.getCantLineas(),"se espera = para realizar comparación");}
break;
//#line 840 "Parser.java"
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
