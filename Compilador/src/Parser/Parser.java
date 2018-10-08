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
    0,    1,    1,    1,    2,    2,    4,    4,    3,    3,
    6,    6,    5,    5,    5,   10,   10,   11,   11,    7,
   12,    7,    7,    7,    8,    8,    8,   13,   13,   13,
   13,   13,   13,   13,   13,    9,    9,    9,    9,    9,
    9,   14,   14,   14,   14,   14,   14,   14,
};
final static short yylen[] = {                            2,
    1,    2,    2,    1,    3,    3,    1,    3,    1,    1,
    2,    2,    5,    7,    2,    1,    1,    3,    2,    4,
    0,    4,    3,    2,    3,    2,    2,    3,    3,    3,
    3,    1,    1,    1,    2,    5,    4,    4,    4,    4,
    4,    1,    1,    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    1,    0,
    0,    9,   10,    0,    0,    0,    0,   32,   33,   34,
    0,    0,    0,   21,    0,    7,    0,    0,    0,    0,
    2,    3,   11,   12,   35,   43,   44,   47,   42,   48,
   45,   46,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    5,    0,    6,    0,    0,    0,    0,    0,
    0,   17,    0,   16,    0,    0,   30,   31,    0,   22,
   20,    8,   39,   40,    0,   38,    0,    0,    0,   13,
   37,   36,   18,    0,   14,
};
final static short yydgoto[] = {                          8,
    9,   10,   11,   27,   12,   13,   14,   15,   22,   63,
   64,   51,   23,   44,
};
final static short yysindex[] = {                       -59,
  -47,  -37,  -40, -254, -254,  -39, -247,    0,    0,  -59,
  -59,    0,    0,  -26,  -20,    0,  -18,    0,    0,    0,
  -56, -215,   33,    0, -230,    0,  -25,  -24, -247,   78,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   12, -247,   -9, -247, -247, -247, -247, -247,
    8,   17,    0, -205,    0,   78,   -5,  -23,   58,   -6,
  -36,    0, -243,    0,    9,    9,    0,    0,   65,    0,
    0,    0,    0,    0,   72,    0,    0, -195,   -9,    0,
    0,    0,    0, -190,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   71,    0,    0,    0,    0,    1,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   18,   20,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   22,    0,    0,    0,   23,    0,    0,    0,    0,
    0,    0,    0,    0,  -30,    5,    0,    0,    0,    0,
    0,    0,    0,    0, -173,    0,  -52,    0,    0,    0,
    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
   13,    0,   30,   87,    0,  -35,    0,    0,    0,   25,
    0,    0,   40,  -15,
};
final static int YYTABLESIZE=305;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         25,
   15,    7,   21,   41,   40,   42,   24,   50,   17,   62,
   28,   16,   28,   26,   28,   79,   80,   74,   54,   54,
   18,   29,   31,   32,    7,   19,   20,   58,   28,   28,
   28,   28,   33,   53,   55,   73,   48,   46,   34,   47,
   35,   49,   45,   62,   52,   29,   30,   29,   70,   29,
   48,    7,   77,   48,   46,   49,   47,   71,   49,   24,
   43,   15,   72,   29,   29,   29,   29,   83,   56,   85,
    4,   41,   40,   42,   48,   46,   27,   47,   26,   49,
   23,   25,   57,   59,   41,   65,   66,   67,   68,   69,
   78,   28,   41,   40,   42,    0,    0,   75,   76,   48,
   46,    0,   47,   84,   49,   81,   48,   46,    0,   47,
    0,   49,   82,   48,   46,    0,   47,    0,   49,   48,
   46,    0,   47,    0,   49,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    1,    2,    0,   17,
    0,    3,    0,    0,    4,    5,   19,   19,    6,    0,
    0,   18,   36,   37,   38,   39,   19,   20,   17,    1,
    2,    0,    0,    0,    3,   28,    0,   28,    0,    0,
   18,    6,   17,    0,   24,   19,   20,   28,   28,   28,
   28,   28,   28,   28,   18,    0,   60,    0,    0,   19,
   20,    3,   61,    0,    0,    0,   15,   15,    6,    0,
   29,   15,   29,   15,   15,   15,    0,   17,   15,    0,
    0,    0,   29,   29,   29,   29,   29,   29,   29,   18,
   36,   37,   38,   39,   19,   20,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   36,   37,   38,   39,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
    0,   61,   40,   60,   61,   62,   59,   23,  256,   45,
   41,   59,   43,  268,   45,  259,  260,   41,   44,   44,
  268,   61,   10,   11,   61,  273,  274,   43,   59,   60,
   61,   62,   59,   59,   59,   41,   42,   43,   59,   45,
   59,   47,  258,   79,  275,   41,    7,   43,   41,   45,
   42,   61,   59,   42,   43,   47,   45,   41,   47,   59,
   21,   61,  268,   59,   60,   61,   62,  263,   29,  260,
    0,   60,   61,   62,   42,   43,   59,   45,   59,   47,
   59,   59,   43,   44,  258,   46,   47,   48,   49,   50,
   61,    5,   60,   61,   62,   -1,   -1,   58,   41,   42,
   43,   -1,   45,   79,   47,   41,   42,   43,   -1,   45,
   -1,   47,   41,   42,   43,   -1,   45,   -1,   47,   42,
   43,   -1,   45,   -1,   47,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  256,  257,   -1,  256,
   -1,  261,   -1,   -1,  264,  265,  259,  260,  268,   -1,
   -1,  268,  269,  270,  271,  272,  273,  274,  256,  256,
  257,   -1,   -1,   -1,  261,  256,   -1,  258,   -1,   -1,
  268,  268,  256,   -1,  275,  273,  274,  268,  269,  270,
  271,  272,  273,  274,  268,   -1,  256,   -1,   -1,  273,
  274,  261,  262,   -1,   -1,   -1,  256,  257,  268,   -1,
  256,  261,  258,  263,  264,  265,   -1,  256,  268,   -1,
   -1,   -1,  268,  269,  270,  271,  272,  273,  274,  268,
  269,  270,  271,  272,  273,  274,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  269,  270,  271,  272,
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
"declaracion : INT identificadores ';'",
"declaracion : ULONG identificadores ';'",
"identificadores : IDENTIFICADOR",
"identificadores : identificadores ',' IDENTIFICADOR",
"sentencias : seleccion",
"sentencias : sentencia",
"sentencia : imprimir ';'",
"sentencia : asignacion ';'",
"seleccion : IF condicion THEN ejecucion ENDIF",
"seleccion : IF condicion THEN ejecucion ELSE ejecucion ENDIF",
"seleccion : error ';'",
"ejecucion : bloque",
"ejecucion : sentencia",
"bloque : BEGIN sentencias END",
"bloque : error ';'",
"imprimir : PRINT '(' CADENA ')'",
"$$1 :",
"imprimir : PRINT CADENA $$1 ')'",
"imprimir : PRINT '(' CADENA",
"imprimir : error ';'",
"asignacion : IDENTIFICADOR '=' operacion",
"asignacion : '=' operacion",
"asignacion : IDENTIFICADOR '='",
"operacion : operacion '+' operacion",
"operacion : operacion '-' operacion",
"operacion : operacion '*' operacion",
"operacion : operacion '/' operacion",
"operacion : IDENTIFICADOR",
"operacion : ENTERO",
"operacion : ENTERO_LARGO",
"operacion : error ';'",
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

//#line 86 "gramatica.y"

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
//#line 337 "Parser.java"
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
{System.out.println("Fin del programa" + val_peek(0).sval + ", linea: " + lexico.getCantLineas());}
break;
case 5:
//#line 17 "gramatica.y"
{System.out.println("Declaracion de tipos, linea: " + lexico.getCantLineas());}
break;
case 6:
//#line 18 "gramatica.y"
{System.out.println("Declaracion de tipos, linea: " + lexico.getCantLineas());}
break;
case 13:
//#line 34 "gramatica.y"
{System.out.println("IF:se esta evaluando una condicion, linea: " + lexico.getCantLineas());}
break;
case 14:
//#line 35 "gramatica.y"
{System.out.println("IF:se esta evaluando una condicion con 2 opciones de ejecucion, linea: " + lexico.getCantLineas());}
break;
case 15:
//#line 36 "gramatica.y"
{System.out.println("Multiples errores dentro de estructura IF, linea: "  + lexico.getCantLineas());}
break;
case 20:
//#line 47 "gramatica.y"
{System.out.println("Sentencia PRINT en linea: " + lexico.getCantLineas());}
break;
case 21:
//#line 48 "gramatica.y"
{System.out.println("Se espera ( en linea: " + lexico.getCantLineas());}
break;
case 23:
//#line 49 "gramatica.y"
{System.out.println("Se espera ) en linea: " + lexico.getCantLineas());}
break;
case 25:
//#line 53 "gramatica.y"
{System.out.println("Asignacion en linea: " + lexico.getCantLineas());}
break;
case 26:
//#line 54 "gramatica.y"
{System.out.println("Se desconoce la variable a la que se asignara el resultado en la linea " + lexico.getCantLineas());}
break;
case 27:
//#line 55 "gramatica.y"
{System.out.println("Se desconoce que se le asignara a la variable, en la linea " + lexico.getCantLineas());}
break;
case 37:
//#line 69 "gramatica.y"
{System.out.println("Se espera ( en la linea: " + lexico.getCantLineas());}
break;
case 38:
//#line 70 "gramatica.y"
{System.out.println("Se espera operando para evaluar condicion en linea: " + lexico.getCantLineas());}
break;
case 39:
//#line 71 "gramatica.y"
{System.out.println("Se espera parte de la condicion para evaluar en linea: " + lexico.getCantLineas());}
break;
case 40:
//#line 72 "gramatica.y"
{System.out.println("Se espera operando para evaluar condicion en linea: " + lexico.getCantLineas());}
break;
case 41:
//#line 73 "gramatica.y"
{System.out.println("Se espera ) en la linea: " + lexico.getCantLineas());}
break;
case 48:
//#line 82 "gramatica.y"
{System.out.println("Se espera = para hacer comparacion, linea " + lexico.getCantLineas());}
break;
//#line 558 "Parser.java"
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
