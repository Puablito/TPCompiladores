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






//#line 2 "LAGRAMATICA.Y"
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
   15,   10,   10,   10,   12,   12,   12,   16,   16,   16,
   17,   17,   17,   18,   18,   18,   18,   13,   13,   13,
   13,   13,   19,   19,   19,   19,   19,   19,   19,
};
final static short yylen[] = {                            2,
    1,    2,    2,    1,    3,    2,    1,    1,    1,    3,
    0,    3,    0,    3,    0,    3,    2,    5,    7,    4,
    4,    6,    6,    4,    3,    1,    1,    3,    2,    2,
    2,    4,    3,    3,    3,    2,    2,    3,    3,    1,
    3,    3,    1,    1,    1,    2,    1,    5,    4,    4,
    4,    4,    1,    1,    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    7,    8,    0,    0,    0,    1,    0,    0,    0,
    0,    0,    0,    0,    6,    0,   44,   45,   47,    0,
    0,    0,   43,    2,    3,    9,    0,    0,    0,    0,
    0,    0,    0,   17,    0,   46,    0,    0,    0,    0,
    5,    0,    0,    0,    0,   12,    0,   14,    0,    0,
   16,    0,    0,   41,   42,   10,   54,   55,   58,   53,
   59,   56,   57,    0,    0,    0,    0,    0,    0,   26,
    0,    0,   25,   33,    0,    0,    0,    0,   30,    0,
    0,   29,    0,   20,    0,   24,   32,   51,    0,   50,
    0,   18,   28,    0,   49,   48,    0,   22,   19,
};
final static short yydgoto[] = {                          6,
   25,    8,   68,   10,   27,   29,   11,   31,   12,   33,
   13,   14,   44,   69,   70,   45,   22,   23,   65,
};
final static short yysindex[] = {                       -39,
  -50,    0,    0,  -30,  -26,    0,    0,  -39,  -39, -247,
 -211, -228, -213,   -7,    0,  -26,    0,    0,    0, -197,
   -8,  -24,    0,    0,    0,    0,  -27,  -37,   -5,  -37,
   23,  -40,   26,    0,   -8,    0,  -26,  -26,  -26,  -26,
    0, -187,   35,  -57,   41,    0,   22,    0,   46, -182,
    0,  -24,  -24,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   41,  -26,  -34,  -41, -169, -219,    0,
  -26,  -34,    0,    0,   57,  -35,   16, -196,    0, -163,
  -39,    0,  -34,    0,   17,    0,    0,    0,   63,    0,
  -34,    0,    0, -153,    0,    0, -151,    0,    0,
};
final static short yyrindex[] = {                      -232,
    0,    0,    0,    0,    0,    0,    0, -232,    1,    0,
    0,    0,    0,    0,    0,   52,    0,    0,    0,    0,
   53,  -17,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   54,    0,    0,    0,    0,    0,
    0,    0,    0, -232,    0,    0, -232,    0,    0,    0,
    0,    8,   30,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0, -232, -232,   -3,    0,    0,
    0, -232,    0,    0,   -7,    0,    0,   55,    0,    0,
  -47,    0, -232,    0,    0,    0,    0,    0,  -59,    0,
 -232,    0,    0,    0,    0,    0,   56,    0,    0,
};
final static short yygindex[] = {                         0,
    5,    0,    7,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   86,  -36,    0,   34,   28,   39,  -31,
};
final static int YYTABLESIZE=313;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         50,
    4,   52,   43,    5,    7,   88,    9,   20,   15,   20,
   73,   31,   24,   71,    9,    9,   42,   39,   20,    5,
   26,    5,   40,   40,   11,   40,    5,   40,   15,   78,
   16,   41,   76,   13,   37,   86,   38,   30,   21,   83,
   84,   40,   40,   40,   40,   28,   94,   32,   38,   35,
   38,   34,   38,   46,   97,   27,   90,   95,   37,   37,
   38,   38,   91,   92,   52,   53,   38,   38,   38,   38,
   39,   80,   39,   81,   39,   36,   64,   54,   55,   20,
   56,   48,    5,   37,   51,   38,   74,    9,   39,   39,
   39,   39,   75,   82,   62,   61,   63,   87,   77,   93,
   62,   61,   63,   96,   85,   37,   98,   38,   99,   89,
   36,   37,   35,   21,   23,   47,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   52,   52,    0,
   66,   52,   52,    0,   67,    0,   52,   52,   52,   11,
    4,   31,   31,   15,    1,    4,    1,    0,   13,    0,
    0,   79,    2,    3,    2,    3,    4,   67,    4,    0,
   17,    0,   17,    4,   49,   18,   19,   18,   19,   40,
   40,   17,    0,   40,   40,    0,   18,   19,   40,   40,
   40,   40,   40,   40,   40,   27,   27,   11,    0,    0,
    0,   15,    0,    4,   38,   38,   13,    0,   38,   38,
    0,    0,    0,   38,   38,   38,   38,   38,   38,   38,
    0,    0,    0,   67,    0,    0,   39,   39,   72,    4,
   39,   39,    0,    0,    0,   39,   39,   39,   39,   39,
   39,   39,   17,   57,   58,   59,   60,   18,   19,   57,
   58,   59,   60,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
    0,   61,   40,   61,    0,   41,    0,   45,   59,   45,
   47,   59,    8,   45,    8,    9,   44,   42,   45,   61,
  268,   61,   47,   41,  257,   43,   61,   45,  261,   66,
   61,   59,   64,  266,   43,   72,   45,  266,    5,  259,
  260,   59,   60,   61,   62,  257,   83,  261,   41,   16,
   43,   59,   45,   59,   91,   59,   41,   41,   43,   43,
   45,   45,  259,  260,   37,   38,   59,   60,   61,   62,
   41,   67,   43,   67,   45,  273,   43,   39,   40,   45,
  268,   59,   61,   43,   59,   45,   41,   81,   59,   60,
   61,   62,  275,  263,   60,   61,   62,   41,   65,  263,
   60,   61,   62,   41,   71,   43,  260,   45,  260,   76,
   59,   59,   59,   59,   59,   30,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,   -1,
  258,  261,  262,   -1,  262,   -1,  266,  267,  268,  257,
  268,  259,  260,  261,  256,  263,  256,   -1,  266,   -1,
   -1,  263,  264,  265,  264,  265,  268,  262,  268,   -1,
  268,   -1,  268,  268,  275,  273,  274,  273,  274,  257,
  258,  268,   -1,  261,  262,   -1,  273,  274,  266,  267,
  268,  269,  270,  271,  272,  259,  260,  257,   -1,   -1,
   -1,  261,   -1,  263,  257,  258,  266,   -1,  261,  262,
   -1,   -1,   -1,  266,  267,  268,  269,  270,  271,  272,
   -1,   -1,   -1,  262,   -1,   -1,  257,  258,  267,  268,
  261,  262,   -1,   -1,   -1,  266,  267,  268,  269,  270,
  271,  272,  268,  269,  270,  271,  272,  273,  274,  269,
  270,  271,  272,
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

//#line 99 "LAGRAMATICA.Y"

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
//#line 355 "Parser.java"
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
//#line 18 "LAGRAMATICA.Y"
{System.out.println("Línea " + lexico.getCantLineas() + ": error en declaración de identificador");}
break;
case 7:
//#line 21 "LAGRAMATICA.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": declaración de tipo INT");}
break;
case 8:
//#line 22 "LAGRAMATICA.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": declaración de tipo ULONG");}
break;
case 9:
//#line 25 "LAGRAMATICA.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": declaración de identificador "+lexico.getyylval());}
break;
case 10:
//#line 26 "LAGRAMATICA.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": declaración de identificador "+lexico.getyylval());}
break;
case 11:
//#line 29 "LAGRAMATICA.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": inicio de IF");}
break;
case 13:
//#line 30 "LAGRAMATICA.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": inicio de WHILE");}
break;
case 15:
//#line 31 "LAGRAMATICA.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": sentencia PRINT");}
break;
case 18:
//#line 35 "LAGRAMATICA.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": fin de IF");}
break;
case 19:
//#line 36 "LAGRAMATICA.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": fin de IF con ELSE");}
break;
case 20:
//#line 37 "LAGRAMATICA.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera THEN");}
break;
case 21:
//#line 38 "LAGRAMATICA.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera ENDIF");}
break;
case 22:
//#line 39 "LAGRAMATICA.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera THEN");}
break;
case 23:
//#line 40 "LAGRAMATICA.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera ENDIF");}
break;
case 24:
//#line 43 "LAGRAMATICA.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": finaliza un ciclo WHILE");}
break;
case 25:
//#line 44 "LAGRAMATICA.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera DO");}
break;
case 29:
//#line 52 "LAGRAMATICA.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera BEGIN");}
break;
case 30:
//#line 53 "LAGRAMATICA.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera alguna sentencia al menos en la linea");}
break;
case 31:
//#line 54 "LAGRAMATICA.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera END");}
break;
case 33:
//#line 58 "LAGRAMATICA.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera (");}
break;
case 34:
//#line 59 "LAGRAMATICA.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera )");}
break;
case 35:
//#line 62 "LAGRAMATICA.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": asignacion");}
break;
case 36:
//#line 63 "LAGRAMATICA.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se desconoce lo que se le asignará luego del =");}
break;
case 37:
//#line 64 "LAGRAMATICA.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se desconoce la variable a la que se le asignará la linea");}
break;
case 38:
//#line 67 "LAGRAMATICA.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": suma");}
break;
case 39:
//#line 68 "LAGRAMATICA.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": resta");}
break;
case 41:
//#line 72 "LAGRAMATICA.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": multiplicacion");}
break;
case 42:
//#line 73 "LAGRAMATICA.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": division");}
break;
case 44:
//#line 77 "LAGRAMATICA.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": identificador");}
break;
case 45:
//#line 78 "LAGRAMATICA.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": entero");}
break;
case 46:
//#line 79 "LAGRAMATICA.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": entero negativo");}
break;
case 47:
//#line 80 "LAGRAMATICA.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": entero largo");}
break;
case 48:
//#line 83 "LAGRAMATICA.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": se evalúa una comparación");}
break;
case 49:
//#line 84 "LAGRAMATICA.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera (");}
break;
case 50:
//#line 85 "LAGRAMATICA.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera operando");}
break;
case 51:
//#line 86 "LAGRAMATICA.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera operando para evaluar la condicion");}
break;
case 52:
//#line 87 "LAGRAMATICA.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera )");}
break;
case 59:
//#line 96 "LAGRAMATICA.Y"
{System.out.println("Línea " + lexico.getCantLineas()+ ": ERROR - se espera = para realizar comparación");}
break;
//#line 656 "Parser.java"
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
