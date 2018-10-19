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






//#line 2 "LAGRAMATICA.y"
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
    7,    3,    9,    3,   11,    3,    3,    6,    6,    6,
    6,    6,    6,    8,    8,   14,   14,   15,   15,   15,
   15,   10,   10,   10,   12,   12,   12,   12,   16,   16,
   16,   16,   16,   16,   16,   16,   18,   17,   17,   17,
   17,   17,   13,   13,   13,   13,   13,   13,   19,   19,
   19,   19,   19,   19,   19,
};
final static short yylen[] = {                            2,
    1,    2,    2,    1,    3,    2,    1,    1,    1,    3,
    0,    3,    0,    3,    0,    3,    2,    5,    7,    4,
    4,    6,    6,    4,    3,    1,    1,    3,    2,    2,
    2,    4,    3,    3,    3,    2,    2,    3,    3,    3,
    3,    3,    1,    1,    1,    2,    1,    2,    4,    4,
    4,    4,    5,    4,    4,    4,    4,    4,    1,    1,
    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    7,    8,    0,    0,    0,    1,    0,    0,    0,
    0,    0,    0,    0,    6,    0,    0,   43,   47,   45,
    0,   44,    2,    3,    9,    0,    0,    0,    0,    0,
    0,    0,   17,    0,    0,   38,   46,    0,    0,    0,
    0,    5,    0,    0,    0,    0,   12,    0,   14,    0,
    0,   16,    0,    0,    0,   41,   42,   10,   60,   61,
   64,   59,   65,   62,   63,    0,    0,    0,    0,    0,
    0,   26,    0,    0,   25,   33,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   30,    0,    0,   29,    0,
   20,    0,   24,   32,    0,    0,    0,    0,   56,   57,
    0,   55,    0,   18,   28,    0,   54,   53,    0,   22,
   19,
};
final static short yydgoto[] = {                          6,
   24,    8,   70,   10,   26,   28,   11,   30,   12,   32,
   13,   14,   45,   71,   72,   46,   36,   22,   67,
};
final static short yysindex[] = {                        -9,
  -50,    0,    0,  -47, -186,    0,    0,   -9,   -9, -250,
 -229, -223, -215,  -12,    0,   38,  -11,    0,    0,    0,
   57,    0,    0,    0,    0,  -33,  -37,   -8,  -37,    4,
  -40,    6,    0, -214,   57,    0,    0, -186, -186, -186,
 -186,    0, -218,  -56,   -5,  -20,    0,  -42,    0,   26,
 -207,    0,  109,  -35,  -35,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   19, -186,  -41,   45, -203,
 -224,    0, -186,  -41,    0,    0,   35, -186, -186, -186,
 -186,   69,   -2,   80, -222,    0, -185,   -9,    0,  -41,
    0,   96,    0,    0,   57,   57,   57,   57,    0,    0,
  103,    0,  -41,    0,    0, -176,    0,    0, -175,    0,
    0,
};
final static short yyrindex[] = {                      -212,
    0,    0,    0,    0,    0,    0,    0, -212,    1,    0,
    0,    0,    0,    0,    0,   30,    0,    0,    0,    0,
  -11,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   37,    0,    0,    0,    0,    0,
    0,    0,    0,    0, -212,    0,    0, -212,    0,    0,
    0,    0,   44,  -28,   12,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0, -212, -212,  -49,
    0,    0,    0, -212,    0,    0,   46,    0,    0,    0,
    0,    0,    0,    0,   48,    0,    0,   39,    0, -212,
    0,    0,    0,    0,    4,   49,   54,    6,    0,    0,
  -59,    0, -212,    0,    0,    0,    0,    0,   56,    0,
    0,
};
final static short yygindex[] = {                         0,
    8,    0,   21,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   89,   27,    0,   53,    0,    0,  -22,
};
final static int YYTABLESIZE=313;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         51,
    4,   58,   44,   64,   63,   65,   40,    7,   15,   27,
   43,   41,   39,   16,   39,   23,   39,   25,    5,    5,
    9,   40,   38,   73,   39,   42,   41,   27,    9,    9,
   39,   39,   39,   39,   90,   91,  103,  104,  100,   64,
   63,   65,   29,   83,   11,   31,   33,   37,   15,   58,
   47,    5,   40,   13,   40,    5,   40,   21,   53,   89,
   40,   38,   49,   39,   52,   41,   76,   77,   35,   17,
   40,   40,   40,   40,   75,   94,   87,  105,   64,   63,
   65,   18,   34,  110,  111,    0,   19,   20,   36,   88,
   54,   55,   56,   57,   85,   35,   66,   31,   40,   38,
   93,   39,   48,   41,   34,    5,   21,   50,    9,   99,
   40,   38,   51,   39,   23,   41,  106,   48,   82,   84,
  102,   40,   38,    0,   39,   92,   41,    0,    0,  109,
   95,   96,   97,   98,    0,  101,  107,   40,   38,    0,
   39,    0,   41,  108,   40,   38,    0,   39,    0,   41,
   80,   78,    0,   79,    0,   81,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   58,   58,   17,
    0,   58,   58,    0,    0,    0,   58,   58,   58,   27,
   27,   18,   59,   60,   61,   62,   19,   20,   17,   69,
   69,    0,    0,    0,   74,    4,    4,   39,   39,   39,
   18,    0,   39,   39,   50,   19,   20,   39,   39,   39,
   39,   39,   39,   39,   39,   39,    1,    0,   59,   60,
   61,   62,   68,   17,    2,    3,   69,   11,    4,    0,
    0,   15,    4,    4,    0,   18,   13,   40,   40,   40,
   19,   20,   40,   40,   17,    0,    0,   40,   40,   40,
   40,   40,   40,   40,   40,   40,   18,   59,   60,   61,
   62,   19,   20,   17,    0,   11,    0,   31,   31,   15,
    1,    4,    0,    0,   13,   18,    0,   86,    2,    3,
   19,   20,    4,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
    0,   61,   40,   60,   61,   62,   42,    0,   59,   59,
   44,   47,   41,   61,   43,    8,   45,  268,   61,   61,
    0,   42,   43,   46,   45,   59,   47,  257,    8,    9,
   59,   60,   61,   62,  259,  260,  259,  260,   41,   60,
   61,   62,  266,   66,  257,  261,   59,   59,  261,  268,
   59,   61,   41,  266,   43,   61,   45,    5,  273,  263,
   42,   43,   59,   45,   59,   47,   41,  275,   16,  256,
   59,   60,   61,   62,   48,   41,   69,  263,   60,   61,
   62,  268,   45,  260,  260,   -1,  273,  274,   59,   69,
   38,   39,   40,   41,   68,   59,   44,   59,   42,   43,
   74,   45,   59,   47,   59,   61,   59,   59,   88,   41,
   42,   43,   59,   45,   59,   47,   90,   29,   66,   67,
   41,   42,   43,   -1,   45,   73,   47,   -1,   -1,  103,
   78,   79,   80,   81,   -1,   83,   41,   42,   43,   -1,
   45,   -1,   47,   41,   42,   43,   -1,   45,   -1,   47,
   42,   43,   -1,   45,   -1,   47,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  256,
   -1,  261,  262,   -1,   -1,   -1,  266,  267,  268,  259,
  260,  268,  269,  270,  271,  272,  273,  274,  256,  262,
  262,   -1,   -1,   -1,  267,  268,  268,  256,  257,  258,
  268,   -1,  261,  262,  275,  273,  274,  266,  267,  268,
  269,  270,  271,  272,  273,  274,  256,   -1,  269,  270,
  271,  272,  258,  256,  264,  265,  262,  257,  268,   -1,
   -1,  261,  268,  263,   -1,  268,  266,  256,  257,  258,
  273,  274,  261,  262,  256,   -1,   -1,  266,  267,  268,
  269,  270,  271,  272,  273,  274,  268,  269,  270,  271,
  272,  273,  274,  256,   -1,  257,   -1,  259,  260,  261,
  256,  263,   -1,   -1,  266,  268,   -1,  263,  264,  265,
  273,  274,  268,
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
"bloque : sentencias END",
"bloque : BEGIN END",
"bloque : BEGIN sentencias",
"imprimir : PRINT '(' CADENA ')'",
"imprimir : PRINT CADENA ')'",
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
"nronegativo : '-' ENTERO '+' operacion",
"nronegativo : '-' ENTERO '-' operacion",
"nronegativo : '-' ENTERO '*' operacion",
"nronegativo : '-' ENTERO '/' operacion",
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

//#line 106 "LAGRAMATICA.y"

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
//#line 369 "Parser.java"
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
//#line 18 "LAGRAMATICA.y"
{System.out.println("Línea " + lexico.getCantLineas() + ": error en declaración de identificador");}
break;
case 7:
//#line 21 "LAGRAMATICA.y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": declaración de tipo INT");}
break;
case 8:
//#line 22 "LAGRAMATICA.y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": declaración de tipo ULONG");}
break;
case 9:
//#line 25 "LAGRAMATICA.y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": declaración de identificador "+lexico.getyylval());}
break;
case 10:
//#line 26 "LAGRAMATICA.y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": declaración de identificador "+lexico.getyylval());}
break;
case 11:
//#line 29 "LAGRAMATICA.y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": inicio de IF");}
break;
case 13:
//#line 30 "LAGRAMATICA.y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": inicio de WHILE");}
break;
case 15:
//#line 31 "LAGRAMATICA.y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": sentencia PRINT");}
break;
case 18:
//#line 35 "LAGRAMATICA.y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": fin de IF");}
break;
case 19:
//#line 36 "LAGRAMATICA.y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": fin de IF con ELSE");}
break;
case 20:
//#line 37 "LAGRAMATICA.y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera THEN");}
break;
case 21:
//#line 38 "LAGRAMATICA.y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera ENDIF");}
break;
case 22:
//#line 39 "LAGRAMATICA.y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera THEN");}
break;
case 23:
//#line 40 "LAGRAMATICA.y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera ENDIF");}
break;
case 24:
//#line 43 "LAGRAMATICA.y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": finaliza un ciclo WHILE");}
break;
case 25:
//#line 44 "LAGRAMATICA.y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera DO");}
break;
case 29:
//#line 52 "LAGRAMATICA.y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera BEGIN");}
break;
case 30:
//#line 53 "LAGRAMATICA.y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera alguna sentencia al menos en la linea");}
break;
case 31:
//#line 54 "LAGRAMATICA.y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera END");}
break;
case 33:
//#line 58 "LAGRAMATICA.y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera (");}
break;
case 34:
//#line 59 "LAGRAMATICA.y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera )");}
break;
case 35:
//#line 62 "LAGRAMATICA.y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": asignacion");}
break;
case 36:
//#line 63 "LAGRAMATICA.y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se desconoce lo que se le asignará luego del =");}
break;
case 37:
//#line 64 "LAGRAMATICA.y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se desconoce la variable a la que se le asignará la linea");}
break;
case 38:
//#line 65 "LAGRAMATICA.y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": asignación numero negativo");}
break;
case 39:
//#line 68 "LAGRAMATICA.y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": operación SUMA");}
break;
case 40:
//#line 69 "LAGRAMATICA.y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": operación RESTA");}
break;
case 41:
//#line 70 "LAGRAMATICA.y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": operación MULTIPLICACION");}
break;
case 42:
//#line 71 "LAGRAMATICA.y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": operación DIVISION");}
break;
case 46:
//#line 75 "LAGRAMATICA.y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR en operación)");}
break;
case 53:
//#line 88 "LAGRAMATICA.y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": se evalúa una comparación");}
break;
case 54:
//#line 89 "LAGRAMATICA.y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera (");}
break;
case 55:
//#line 90 "LAGRAMATICA.y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera operando");}
break;
case 56:
//#line 91 "LAGRAMATICA.y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera parte de la condicion para evaluar");}
break;
case 57:
//#line 92 "LAGRAMATICA.y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera operando para evaluar la condicion");}
break;
case 58:
//#line 93 "LAGRAMATICA.y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera )");}
break;
case 65:
//#line 102 "LAGRAMATICA.y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera = para realizar comparación");}
break;
//#line 666 "Parser.java"
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
