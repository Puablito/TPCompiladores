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






//#line 2 "gramatica.y"
	package Parser;
	import principal.*;
//#line 20 "Parser.java"




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
    0,    1,    1,    1,    6,    2,    2,    4,    4,    5,
    5,    3,    3,    3,    3,    7,    7,    7,    7,    7,
    7,    8,    8,   12,   12,   13,   14,   13,   13,   13,
    9,   15,    9,    9,   10,   10,   10,   10,   16,   16,
   16,   16,   16,   16,   16,   16,   18,   17,   11,   11,
   11,   11,   11,   11,   19,   19,   19,   19,   19,   19,
   19,
};
final static short yylen[] = {                            2,
    1,    2,    2,    1,    0,    6,    2,    1,    1,    1,
    3,    2,    2,    2,    2,    5,    7,    4,    4,    6,
    6,    4,    3,    1,    1,    3,    0,    3,    2,    2,
    4,    0,    4,    3,    3,    2,    2,    3,    3,    3,
    3,    3,    1,    1,    1,    2,    1,    2,    5,    4,
    4,    4,    4,    4,    1,    1,    1,    1,    1,    1,
    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    8,    9,    0,    0,    0,    0,    1,
    0,    0,    0,    0,    0,    0,    0,    7,    0,   43,
   47,   45,    0,    0,    0,   44,   32,    0,    0,    0,
    0,    2,    3,   10,    0,   12,   13,   14,   15,   46,
   56,   57,   60,   55,   61,   58,   59,    0,    0,    0,
    0,    0,    0,   24,    0,    0,    0,    0,    0,    0,
    0,    0,   23,    0,    0,   38,    5,    0,    0,    0,
    0,    0,   29,    0,    0,    0,    0,   18,    0,    0,
   41,   42,    0,   33,   31,   22,   48,    0,   11,   52,
   53,    0,   51,    0,   16,   26,   28,    0,   50,    0,
   49,    0,   20,    0,   17,
};
final static short yydgoto[] = {                          9,
   33,   11,   52,   13,   35,   88,   14,   15,   16,   17,
   24,   53,   54,   76,   60,   25,   66,   26,   49,
};
final static short yysindex[] = {                        45,
  -45,  -37,  -40,    0,    0,  -37,  -43, -247,    0,    0,
   45,   45, -246,  -34,  -18,  -16,  -13,    0,   -7,    0,
    0,    0,  -56,   77,   26,    0,    0, -221,   80,   31,
  107,    0,    0,    0,  -36,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   20, -247,   92,
  -59,    0, -249,    0, -247, -247, -247, -247, -247,   15,
   23,   92,    0, -216,  107,    0,    0, -196,    8,    6,
   60, -231,    0, -186,   45, -184,   92,    0,  -35,  -35,
    0,    0,   86,    0,    0,    0,    0, -227,    0,    0,
    0,  101,    0,   92,    0,    0,    0, -177,    0, -246,
    0, -176,    0,   41,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    1,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  -34,
  -18,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  -39,    0,    0,    0,    0,    0,    0,    0,    0,
   32,    0,    0,    0,   33,    0,    0,    0,    0,    0,
    0,   35,    0,    0,  -11,    0,    0,    0,  -28,   -1,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   57,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   36,    0,   65,    0,
};
final static short yygindex[] = {                         0,
   19,    0,   24,    2,   -3,    0,    0,    0,    0,    0,
   94,   16,    0,    0,    0,   66,    0,    0,   -9,
};
final static int YYTABLESIZE=360;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         28,
    4,    8,   23,   46,   45,   47,   57,   68,   19,   77,
   78,   58,   39,   18,   39,   59,   39,   30,   10,   25,
   20,   34,   67,   12,   36,   21,   22,   94,   95,   32,
   39,   39,   39,   39,   12,   12,    4,    5,   70,   40,
   37,   40,   38,   40,   63,   39,   91,   30,   90,   57,
   55,   40,   56,   61,   58,   84,   87,   40,   40,   40,
   40,   57,   55,   85,   56,   72,   58,   57,   55,   74,
   56,   89,   58,   31,   75,   64,   96,   86,   97,   46,
   45,   47,  103,  105,   68,   46,   45,   47,   48,  100,
   34,   35,   98,   19,   21,   65,  104,    0,   12,   29,
   93,   57,   55,    0,   56,    8,   58,    0,    0,  102,
    0,    0,    0,   69,   71,    0,    0,   54,    0,    0,
   79,   80,   81,   82,   83,    6,   99,   57,   55,    0,
   56,    0,   58,    0,    0,   92,    0,    8,    0,    0,
    8,  101,   57,   55,    0,   56,    0,   58,   57,   55,
    0,   56,    8,   58,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    1,    2,    0,   19,
    0,    3,    0,   73,    4,    5,    6,    0,    7,    0,
    0,   20,   41,   42,   43,   44,   21,   22,   19,   25,
   25,    0,    0,   27,    0,    0,    0,   39,   39,   39,
   20,    0,   39,   39,   27,   21,   22,   39,   39,   39,
   39,   39,   39,   39,   39,   39,    0,   30,   30,    0,
    0,    4,    0,    0,   40,   40,   40,    0,    0,   40,
   40,   19,    0,    4,   40,   40,   40,   40,   40,   40,
   40,   40,   40,   20,    0,   19,    0,    0,   21,   22,
    0,    0,    0,    0,    0,    0,   19,   20,   41,   42,
   43,   44,   21,   22,   41,   42,   43,   44,   20,    0,
    1,    2,    0,   21,   22,    3,    0,    0,    4,    5,
    6,    0,    7,   54,   54,    0,    0,   54,   54,    0,
    6,    6,   54,   54,   54,    6,    0,    0,    6,    6,
    6,    0,    6,    2,   50,    0,    2,    3,   51,    0,
    3,   51,    6,    0,    7,    6,   62,    7,    2,    0,
    0,    0,    3,   51,    0,    0,    0,    6,    0,    7,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
    0,   61,   40,   60,   61,   62,   42,   44,  256,  259,
  260,   47,   41,   59,   43,   25,   45,   61,    0,   59,
  268,  268,   59,    0,   59,  273,  274,  259,  260,   11,
   59,   60,   61,   62,   11,   12,  264,  265,   48,   41,
   59,   43,   59,   45,   29,   59,   41,   59,   41,   42,
   43,   59,   45,  275,   47,   41,  273,   59,   60,   61,
   62,   42,   43,   41,   45,   50,   47,   42,   43,   51,
   45,  268,   47,    8,   51,   45,  263,   62,  263,   60,
   61,   62,  260,  260,   44,   60,   61,   62,   23,   88,
   59,   59,   77,   59,   59,   30,  100,   -1,   75,    6,
   41,   42,   43,   -1,   45,   61,   47,   -1,   -1,   94,
   -1,   -1,   -1,   48,   49,   -1,   -1,   61,   -1,   -1,
   55,   56,   57,   58,   59,   61,   41,   42,   43,   -1,
   45,   -1,   47,   -1,   -1,   70,   -1,   61,   -1,   -1,
   61,   41,   42,   43,   -1,   45,   -1,   47,   42,   43,
   -1,   45,   61,   47,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  256,  257,   -1,  256,
   -1,  261,   -1,  263,  264,  265,  266,   -1,  268,   -1,
   -1,  268,  269,  270,  271,  272,  273,  274,  256,  259,
  260,   -1,   -1,  263,   -1,   -1,   -1,  256,  257,  258,
  268,   -1,  261,  262,  275,  273,  274,  266,  267,  268,
  269,  270,  271,  272,  273,  274,   -1,  259,  260,   -1,
   -1,  263,   -1,   -1,  256,  257,  258,   -1,   -1,  261,
  262,  256,   -1,  263,  266,  267,  268,  269,  270,  271,
  272,  273,  274,  268,   -1,  256,   -1,   -1,  273,  274,
   -1,   -1,   -1,   -1,   -1,   -1,  256,  268,  269,  270,
  271,  272,  273,  274,  269,  270,  271,  272,  268,   -1,
  256,  257,   -1,  273,  274,  261,   -1,   -1,  264,  265,
  266,   -1,  268,  257,  258,   -1,   -1,  261,  262,   -1,
  256,  257,  266,  267,  268,  261,   -1,   -1,  264,  265,
  266,   -1,  268,  257,  258,   -1,  257,  261,  262,   -1,
  261,  262,  266,   -1,  268,  266,  267,  268,  257,   -1,
   -1,   -1,  261,  262,   -1,   -1,   -1,  266,   -1,  268,
};
}
final static short YYFINAL=9;
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
"$$1 :",
"declaracion : tipo identificadores ';' $$1 tipo identificadores",
"declaracion : error ';'",
"tipo : INT",
"tipo : ULONG",
"identificadores : IDENTIFICADOR",
"identificadores : identificadores ',' IDENTIFICADOR",
"sentencias : seleccion ';'",
"sentencias : mientras ';'",
"sentencias : imprimir ';'",
"sentencias : asignacion ';'",
"seleccion : IF condicion THEN ejecucion ENDIF",
"seleccion : IF condicion THEN ejecucion ELSE ejecucion ENDIF",
"seleccion : IF condicion ejecucion ENDIF",
"seleccion : IF condicion THEN ejecucion",
"seleccion : IF condicion ejecucion ELSE ejecucion ENDIF",
"seleccion : IF condicion THEN ejecucion ELSE ejecucion",
"mientras : WHILE condicion DO ejecucion",
"mientras : WHILE condicion ejecucion",
"ejecucion : bloque",
"ejecucion : sentencias",
"bloque : BEGIN codigo END",
"$$2 :",
"bloque : sentencias $$2 END",
"bloque : BEGIN END",
"bloque : BEGIN sentencias",
"imprimir : PRINT '(' CADENA ')'",
"$$3 :",
"imprimir : PRINT CADENA $$3 ')'",
"imprimir : PRINT '(' CADENA",
"asignacion : IDENTIFICADOR '=' operacion",
"asignacion : IDENTIFICADOR '='",
"asignacion : '=' operacion",
"asignacion : IDENTIFICADOR '=' nronegativo",
"operacion : operacion '+' operacion",
"operacion : operacion '-' operacion",
"operacion : operacion '*' operacion",
"operacion : operacion '/' operacion",
"operacion : IDENTIFICADOR",
"operacion : entero",
"operacion : ENTERO_LARGO",
"operacion : error ';'",
"entero : ENTERO",
"nronegativo : '-' ENTERO",
"condicion : '(' operacion comparador operacion ')'",
"condicion : operacion comparador operacion ')'",
"condicion : '(' comparador operacion ')'",
"condicion : '(' operacion operacion ')'",
"condicion : '(' operacion comparador ')'",
"condicion : '(' operacion comparador operacion",
"comparador : MAYOROIGUAL",
"comparador : MENOROIGUAL",
"comparador : DISTINTO",
"comparador : '<'",
"comparador : '>'",
"comparador : IGUAL",
"comparador : '='",
};

//#line 103 "gramatica.y"

static Lexico lexico;

public Parser(Lexico analizadorLexico)
{
	lexico = analizadorLexico;
  //nothing to do
}
private void yyerror(String string) {
	//no se si lo usaremos
}
private int yylex() {
	int tokenNro;
	
	tokenNro = 0;
	if (!lexico.isFinArchivo()) {
		tokenNro = lexico.getToken();
		yylval = new ParserVal(lexico.getyylval());
	}
	
	return tokenNro;
}
//#line 370 "Parser.java"
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
case 1:
//#line 9 "gramatica.y"
{System.out.println("Fin del programa linea: " + lexico.getCantLineas());}
break;
case 5:
//#line 17 "gramatica.y"
{System.out.println("Declaracion de tipos, linea: " + lexico.getCantLineas());}
break;
case 7:
//#line 19 "gramatica.y"
{System.out.println("Error en declaracion de tipos, linea: " + lexico.getCantLineas());}
break;
case 8:
//#line 22 "gramatica.y"
{System.out.println("Declaracion de tipo INT, linea: " + lexico.getCantLineas());}
break;
case 9:
//#line 23 "gramatica.y"
{System.out.println("Declaracion de tipo ULONG, linea: " + lexico.getCantLineas());}
break;
case 10:
//#line 26 "gramatica.y"
{System.out.println("Identificador "+ lexico.getyylval()+", linea: " + lexico.getCantLineas());}
break;
case 11:
//#line 27 "gramatica.y"
{System.out.println("Identificador "+ lexico.getyylval()+", linea: " + lexico.getCantLineas());}
break;
case 16:
//#line 36 "gramatica.y"
{System.out.println("IF:se esta evaluando una condicion, linea: " + lexico.getCantLineas());}
break;
case 17:
//#line 37 "gramatica.y"
{System.out.println("IF:se esta evaluando una condicion con 2 opciones de ejecucion, linea: " + lexico.getCantLineas());}
break;
case 18:
//#line 38 "gramatica.y"
{System.out.println("Se espera THEN en la linea: " + lexico.getCantLineas());}
break;
case 19:
//#line 39 "gramatica.y"
{System.out.println("Se espera ENDIF en la linea: " + lexico.getCantLineas());}
break;
case 20:
//#line 40 "gramatica.y"
{System.out.println("Se espera THEN en la linea: " + lexico.getCantLineas());}
break;
case 21:
//#line 41 "gramatica.y"
{System.out.println("Se espera ENDIF en la linea: " + lexico.getCantLineas());}
break;
case 22:
//#line 44 "gramatica.y"
{System.out.println("Finaliza un while en linea: " + lexico.getCantLineas());}
break;
case 23:
//#line 45 "gramatica.y"
{System.out.println("Se espera DO en la linea: " + lexico.getCantLineas());}
break;
case 26:
//#line 52 "gramatica.y"
{System.out.println("BLOQUE encontrado: " + lexico.getCantLineas());}
break;
case 27:
//#line 53 "gramatica.y"
{System.out.println("Se espera BEGIN en la linea: " + lexico.getCantLineas());}
break;
case 29:
//#line 54 "gramatica.y"
{System.out.println("Se espera alguna sentencia al menos en la linea: " + lexico.getCantLineas());}
break;
case 30:
//#line 55 "gramatica.y"
{System.out.println("Se espera END en la linea: " + lexico.getCantLineas());}
break;
case 31:
//#line 58 "gramatica.y"
{System.out.println("Sentencia PRINT en linea: " + lexico.getCantLineas());}
break;
case 32:
//#line 59 "gramatica.y"
{System.out.println("Se espera ( en linea: " + lexico.getCantLineas());}
break;
case 34:
//#line 60 "gramatica.y"
{System.out.println("Se espera ) en linea: " + lexico.getCantLineas());}
break;
case 35:
//#line 63 "gramatica.y"
{System.out.println("Asignacion en linea: " + lexico.getCantLineas());}
break;
case 36:
//#line 64 "gramatica.y"
{System.out.println("Se desconoce que se le asignara a la variable, en la linea " + lexico.getCantLineas());}
break;
case 37:
//#line 65 "gramatica.y"
{System.out.println("Se desconoce la variable a la que se asignara el resultado en la linea " + lexico.getCantLineas());}
break;
case 38:
//#line 66 "gramatica.y"
{System.out.println("Asignacion numero negativo en linea: " + lexico.getCantLineas());}
break;
case 39:
//#line 69 "gramatica.y"
{System.out.println("Operacion SUMA: " + lexico.getCantLineas());}
break;
case 40:
//#line 70 "gramatica.y"
{System.out.println("Operacion RESTA: " + lexico.getCantLineas());}
break;
case 41:
//#line 71 "gramatica.y"
{System.out.println("Operacion MULTIPLICACION: " + lexico.getCantLineas());}
break;
case 42:
//#line 72 "gramatica.y"
{System.out.println("Operacion DIVISION: " + lexico.getCantLineas());}
break;
case 43:
//#line 73 "gramatica.y"
{System.out.println("Operacion IDENTIFICADOR: " + lexico.getCantLineas());}
break;
case 44:
//#line 74 "gramatica.y"
{System.out.println("Operacion ENTERO: " + lexico.getCantLineas());}
break;
case 45:
//#line 75 "gramatica.y"
{System.out.println("Operacion ENTERO_LARGO: " + lexico.getCantLineas());}
break;
case 46:
//#line 76 "gramatica.y"
{System.out.println("ERROR en Operacion: " + lexico.getCantLineas());}
break;
case 49:
//#line 85 "gramatica.y"
{System.out.println("analiza condicion: " + lexico.getCantLineas());}
break;
case 50:
//#line 86 "gramatica.y"
{System.out.println("Se espera ( en la linea: " + lexico.getCantLineas());}
break;
case 51:
//#line 87 "gramatica.y"
{System.out.println("Se espera operando para evaluar condicion en linea: " + lexico.getCantLineas());}
break;
case 52:
//#line 88 "gramatica.y"
{System.out.println("Se espera parte de la condicion para evaluar en linea: " + lexico.getCantLineas());}
break;
case 53:
//#line 89 "gramatica.y"
{System.out.println("Se espera operando para evaluar condicion en linea: " + lexico.getCantLineas());}
break;
case 54:
//#line 90 "gramatica.y"
{System.out.println("Se espera ) en la linea: " + lexico.getCantLineas());}
break;
case 55:
//#line 93 "gramatica.y"
{System.out.println("Comparador MAYOROIGUAL: " + lexico.getCantLineas());}
break;
case 56:
//#line 94 "gramatica.y"
{System.out.println("Comparador MENOROIGUAL: " + lexico.getCantLineas());}
break;
case 57:
//#line 95 "gramatica.y"
{System.out.println("Comparador DISTINTO: " + lexico.getCantLineas());}
break;
case 58:
//#line 96 "gramatica.y"
{System.out.println("Comparador <: " + lexico.getCantLineas());}
break;
case 59:
//#line 97 "gramatica.y"
{System.out.println("Comparador >: " + lexico.getCantLineas());}
break;
case 60:
//#line 98 "gramatica.y"
{System.out.println("Comparador IGUAL: " + lexico.getCantLineas());}
break;
case 61:
//#line 99 "gramatica.y"
{System.out.println("Se espera = para hacer comparacion, linea " + lexico.getCantLineas());}
break;
//#line 707 "Parser.java"
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
