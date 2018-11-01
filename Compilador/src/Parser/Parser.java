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






//#line 2 "LAGRAMATICATP3.Y"
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
    0,    1,    1,    1,    2,    2,    4,    4,    5,    5,
    7,    3,    9,    3,   11,    3,    3,    6,   13,   13,
   13,   18,   14,   14,   14,   14,   17,   17,    8,   19,
   19,   19,   16,   16,   20,   20,   20,   20,   10,   10,
   10,   12,   12,   12,   21,   21,   21,   22,   22,   22,
   23,   23,   23,   23,   15,   15,   15,   15,   15,   24,
   24,   24,   24,   24,   24,   24,
};
final static short yylen[] = {                            2,
    1,    2,    2,    1,    3,    2,    1,    1,    1,    3,
    0,    3,    0,    3,    0,    3,    2,    2,    3,    2,
    2,    0,    4,    2,    2,    1,    2,    1,    2,    3,
    2,    2,    1,    1,    3,    2,    2,    2,    4,    3,
    3,    3,    2,    2,    3,    3,    1,    3,    3,    1,
    1,    1,    2,    1,    5,    4,    4,    4,    4,    1,
    1,    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    7,    8,    0,    0,    0,    1,    0,    0,    0,
    0,    0,    0,    0,    6,    0,   51,   52,   54,    0,
    0,    0,   50,    2,    3,    9,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   17,    0,   53,    0,    0,
    0,    0,    5,    0,   21,    0,    0,    0,   12,    0,
   26,    0,    0,   18,    0,   33,   32,    0,   14,   29,
    0,    0,   16,    0,    0,   48,   49,   10,   61,   62,
   65,   60,   66,   63,   64,    0,    0,   19,    0,   28,
    0,   25,   37,    0,    0,   36,   22,   24,   30,   40,
    0,    0,    0,    0,   27,   35,    0,   39,   58,    0,
   57,   56,   23,   55,
};
final static short yydgoto[] = {                          6,
   25,    8,    9,   10,   27,   29,   11,   32,   12,   35,
   13,   14,   30,   54,   47,   81,   82,   97,   33,   56,
   48,   22,   23,   77,
};
final static short yysindex[] = {                        20,
  -27,    0,    0,  -52,   -8,    0,    0,   20,   20, -225,
 -196, -200, -186,   21,    0,   -8,    0,    0,    0, -194,
   -1,  -16,    0,    0,    0,    0,  -26,  -37,   23,   83,
  -35,   26,  -34,  -40,   28,    0,   -1,    0,   -8,   -8,
   -8,   -8,    0, -191,    0,  -21, -170,  -15,    0,  -50,
    0,  -39, -174,    0, -239,    0,    0, -177,    0,    0,
   50, -178,    0,  -16,  -16,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -15,   -8,    0,   -8,    0,
 -159,    0,    0, -161,   20,    0,    0,    0,    0,    0,
   62,  -28,   14,   19,    0,    0,  -50,    0,    0,   22,
    0,    0,    0,    0,
};
final static short yyrindex[] = {                      -232,
    0,    0,    0,    0,    0,    0,    0, -232,    1,    0,
    0,    0,    0,    0,    0,   45,    0,    0,    0,    0,
   46,   11,    0,    0,    0,    0,    0,    0,    0, -232,
    0,    0, -232,    0,    0,    0,   47,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  -46,    0,    0, -232,
    0, -232,  -55,    0,    0,    0,    0,   78,    0,    0,
    0,    0,    0,   33,   55,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   71,    0,    0,    0,    0,    0,
   48,    0,    0,    0,    0,    0, -232,    0,    0,  -59,
    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    6,    0,  -14,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   77,   18,   12,    0,    0,    0,
    7,   10,   27,  -41,
};
final static int YYTABLESIZE=351;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         62,
    4,   59,   46,   34,   46,    7,   79,   20,   16,   20,
    5,   21,   99,   24,   20,   53,   20,   44,   53,   87,
   88,    5,   37,   20,   11,   41,    5,   39,   15,   40,
   42,   15,   43,   13,   92,   53,   20,   85,   74,   73,
   75,   39,   26,   40,   74,   73,   75,   55,   64,   65,
   60,   47,   76,   47,  101,   47,   39,   84,   40,  102,
   28,   39,  104,   40,   39,   31,   40,   66,   67,   47,
   47,   47,   47,   45,   34,   45,   68,   45,   38,   36,
    5,   49,   53,   93,   59,   94,   63,   78,   86,   89,
   90,   45,   45,   45,   45,   46,   91,   46,  100,   46,
   95,   96,   98,   43,   44,   42,   41,   58,  103,    0,
    0,    0,    0,   46,   46,   46,   46,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   38,
    0,    0,    0,    0,    0,    0,    0,    0,   31,    0,
    0,    0,    0,    5,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   59,   59,   59,
   59,   59,   59,   34,   34,    0,   59,   59,   59,   80,
   20,   52,   20,   20,   20,   20,    1,    4,    0,   20,
   45,   20,    0,   83,    2,    3,    0,   52,    4,    0,
   17,   57,   17,    4,   61,   18,   19,   18,   19,   17,
    0,    0,    0,    0,   18,   19,   17,   69,   70,   71,
   72,   18,   19,   69,   70,   71,   72,   11,    0,   17,
    0,   15,    0,    4,   18,   19,   13,   47,   47,   47,
   47,   47,   47,    0,    0,    1,   47,   47,   47,   47,
   47,   47,   47,    2,    3,    0,    0,    4,    0,   45,
   45,   45,   45,   45,   45,    0,    0,    0,   45,   45,
   45,   45,   45,   45,   45,    0,    0,    0,    0,    0,
    0,   46,   46,   46,   46,   46,   46,    0,    0,    0,
   46,   46,   46,   46,   46,   46,   46,   11,    0,   38,
   38,   15,    0,    4,   31,    0,   13,    0,   31,   31,
    0,   50,   51,   31,   52,   31,    0,    0,    0,    0,
    4,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
    0,   61,   40,   59,   40,    0,   48,   45,   61,   45,
   61,    5,   41,    8,   61,   30,   45,   44,   33,  259,
  260,   61,   16,   45,  257,   42,   61,   43,  261,   45,
   47,   59,   59,  266,   76,   50,   45,   52,   60,   61,
   62,   43,  268,   45,   60,   61,   62,   30,   39,   40,
   33,   41,   46,   43,   41,   45,   43,   52,   45,   41,
  257,   43,   41,   45,   43,  266,   45,   41,   42,   59,
   60,   61,   62,   41,  261,   43,  268,   45,  273,   59,
   61,   59,   97,   77,   59,   79,   59,  258,  263,  267,
   41,   59,   60,   61,   62,   41,  275,   43,   92,   45,
  260,  263,   41,   59,   59,   59,   59,   31,   97,   -1,
   -1,   -1,   -1,   59,   60,   61,   62,   -1,   -1,   -1,
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
"cuerpoif : ejecucion ENDIF",
"cuerpoif : ELSE falsoif",
"cuerpoif : ENDIF",
"falsoif : ejecucion ENDIF",
"falsoif : ENDIF",
"mientras : mientrasencabezado ejecucion",
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

//#line 114 "LAGRAMATICATP3.Y"

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
//#line 377 "Parser.java"
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
//#line 18 "LAGRAMATICATP3.Y"
{System.out.println("Línea " + lexico.getCantLineas() + ": error en declaración de identificador");}
break;
case 7:
//#line 21 "LAGRAMATICATP3.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": declaración de tipo INT");}
break;
case 8:
//#line 22 "LAGRAMATICATP3.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": declaración de tipo ULONG");}
break;
case 9:
//#line 25 "LAGRAMATICATP3.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": declaración de identificador "+lexico.getyylval());}
break;
case 10:
//#line 26 "LAGRAMATICATP3.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": declaración de identificador "+lexico.getyylval());}
break;
case 11:
//#line 29 "LAGRAMATICATP3.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": inicio de IF");}
break;
case 13:
//#line 30 "LAGRAMATICATP3.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": inicio de WHILE");}
break;
case 15:
//#line 31 "LAGRAMATICATP3.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": sentencia PRINT");}
break;
case 19:
//#line 38 "LAGRAMATICATP3.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": encabezado de IF");}
break;
case 20:
//#line 39 "LAGRAMATICATP3.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera THEN");}
break;
case 21:
//#line 40 "LAGRAMATICATP3.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera condicion para evaluar");}
break;
case 22:
//#line 43 "LAGRAMATICATP3.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ELSE");}
break;
case 24:
//#line 44 "LAGRAMATICATP3.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": fin if simple");}
break;
case 25:
//#line 45 "LAGRAMATICATP3.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera ejecucion por verdadero");}
break;
case 26:
//#line 46 "LAGRAMATICATP3.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera ejecucion por verdadero");}
break;
case 27:
//#line 50 "LAGRAMATICATP3.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": fin if doble");}
break;
case 28:
//#line 51 "LAGRAMATICATP3.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera ejecucion por falso");}
break;
case 29:
//#line 54 "LAGRAMATICATP3.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": finaliza un ciclo WHILE");}
break;
case 30:
//#line 57 "LAGRAMATICATP3.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": encabezado de WHILE");}
break;
case 31:
//#line 58 "LAGRAMATICATP3.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera DO");}
break;
case 32:
//#line 59 "LAGRAMATICATP3.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera condicion para evaluar");}
break;
case 36:
//#line 67 "LAGRAMATICATP3.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera BEGIN");}
break;
case 37:
//#line 68 "LAGRAMATICATP3.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera alguna sentencia al menos en la linea");}
break;
case 38:
//#line 69 "LAGRAMATICATP3.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera END");}
break;
case 40:
//#line 73 "LAGRAMATICATP3.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera (");}
break;
case 41:
//#line 74 "LAGRAMATICATP3.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera )");}
break;
case 42:
//#line 77 "LAGRAMATICATP3.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": asignacion");}
break;
case 43:
//#line 78 "LAGRAMATICATP3.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se desconoce lo que se le asignará luego del =");}
break;
case 44:
//#line 79 "LAGRAMATICATP3.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se desconoce la variable a la que se le asignará la linea");}
break;
case 45:
//#line 82 "LAGRAMATICATP3.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": suma");}
break;
case 46:
//#line 83 "LAGRAMATICATP3.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": resta");}
break;
case 48:
//#line 87 "LAGRAMATICATP3.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": multiplicacion");}
break;
case 49:
//#line 88 "LAGRAMATICATP3.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": division");}
break;
case 51:
//#line 92 "LAGRAMATICATP3.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": identificador");}
break;
case 52:
//#line 93 "LAGRAMATICATP3.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": entero");}
break;
case 53:
//#line 94 "LAGRAMATICATP3.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": entero negativo");}
break;
case 54:
//#line 95 "LAGRAMATICATP3.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": entero largo");}
break;
case 55:
//#line 98 "LAGRAMATICATP3.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": se evalúa una comparación");}
break;
case 56:
//#line 99 "LAGRAMATICATP3.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera (");}
break;
case 57:
//#line 100 "LAGRAMATICATP3.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera operando");}
break;
case 58:
//#line 101 "LAGRAMATICATP3.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera operando para evaluar la condicion");}
break;
case 59:
//#line 102 "LAGRAMATICATP3.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera )");}
break;
case 66:
//#line 111 "LAGRAMATICATP3.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera = para realizar comparación");}
break;
//#line 698 "Parser.java"
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
