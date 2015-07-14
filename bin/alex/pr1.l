package alex;

import errors.GestionErroresTiny;

%%
%cup
%line
%class AnalizadorLexicoTiny
%unicode
%public

%{
  private ALexOperations ops;
  private GestionErroresTiny errores;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public void fijaGestionErrores(GestionErroresTiny errores) {
   this.errores = errores;
  }
%}

%eofval{
  return ops.unidadEof();
%eofval}

%init{
  ops = new ALexOperations(this);
%init}

letra  = ([A-Z]|[a-z])
digitoPos = [1-9]
digito = ({digitoPos}|0)
parteDecimal = ({digito}*{digitoPos})
exp = (e|E)(\-)?(0|({digitoPos}{digito}*))
espacio = [ \t\r\b\n]
comentario = @[^\n]* 
tipo = tipo
int = int
real = real
rec = rec
endrec = endrec
pointer = pointer
object = object
extends = extends
endobject = endobject
fun = fun
method = method
returns = returns
end = end
this = this
super = super
null = null
in = in
out = out
alloc = alloc
free = free
if = if
else = else
then = then
elsif = elsif
endif = endif
while = while
do = do
endwhile = endwhile
return = return
identificador = (_|{letra})({letra}|{digito}|_)*
numEntero = (0|({digitoPos}{digito}*))
numReal = {numEntero}((\.{parteDecimal})|{exp}|(\.{parteDecimal}{exp}))
separador = #
pycoma = \;
cApertura = \[
cCierre = \]
referencia = \&
pAp = \(
pCierre = \)
punto = \.
apostrofe = \u005E
menor = \<
mayor = \>
menorIgual = \<\=
mayorIgual = \>\=
igual = \=\=
distinto = \!\=
or = \|\|
mas = \+
menos = \-
por = \*
div = /
mod = \%
and = \&\&
not = \!
coma  = \,
asig = \=
castInt = {pAp}{int}{pCierre}
castReal = {pAp}{real}{pCierre}
%%
{espacio}                {}
{comentario}              {}
{tipo}		  	  {return ops.unidadTipo();}
{int}			  {return ops.unidadInt();}
{real}			  {return ops.unidadReal();}
{rec}			  {return ops.unidadRec();}
{endrec}		  {return ops.unidadEndrec();}
{pointer}		  {return ops.unidadPointer();}
{object}		  {return ops.unidadObject();}
{extends}		  {return ops.unidadExtends();}
{endobject}		  {return ops.unidadEndobject();}
{fun}			  {return ops.unidadFun();}
{method}		  {return ops.unidadMethod();}
{returns}		  {return ops.unidadReturns();}
{end}			  {return ops.unidadEnd();}
{this}			  {return ops.unidadThis();}
{super}			  {return ops.unidadSuper();}
{null}			  {return ops.unidadNull();}
{in}			  {return ops.unidadIn();}
{out}			  {return ops.unidadOut();}
{alloc}			  {return ops.unidadAlloc();}
{free}			  {return ops.unidadFree();}
{if}			  {return ops.unidadIf();}
{else}			  {return ops.unidadElse();}
{then}			  {return ops.unidadThen();}
{elsif}			  {return ops.unidadElsif();}
{endif}			  {return ops.unidadEndif();}
{while}			  {return ops.unidadWhile();}
{do}			  {return ops.unidadDo();}
{endwhile}		  {return ops.unidadEndwhile();}
{return}		  {return ops.unidadReturn();}
{identificador}		  {return ops.unidadId();}
{numEntero}		  {return ops.unidadNumEntero();}
{numReal}		  {return ops.unidadNumReal();}
{separador}		  {return ops.unidadSep();}
{pycoma}		  {return ops.unidadPycoma();}
{cApertura}		  {return ops.unidadCapertura();}
{cCierre}		  {return ops.unidadCcierre();}
{referencia}		  {return ops.unidadReferencia();}
{pAp}			  {return ops.unidadPap();}
{pCierre}		  {return ops.unidadPcierre();}
{punto}			  {return ops.unidadPunto();}
{apostrofe}		  {return ops.unidadApostrofe();}
{menor}			  {return ops.unidadMenor();}
{mayor}			  {return ops.unidadMayor();}
{menorIgual}		  {return ops.unidadMenorIgual();}
{mayorIgual}	          {return ops.unidadMayorIgual();}
{igual}			  {return ops.unidadIgual();}
{distinto}		  {return ops.unidadDistinto();}
{or}			  {return ops.unidadOr();}
{mas}			  {return ops.unidadMas();}
{menos}			  {return ops.unidadMenos();}
{por}			  {return ops.unidadPor();}
{div}			  {return ops.unidadDiv();}
{mod}			  {return ops.unidadMod();}
{and}			  {return ops.unidadAnd();}
{not}			  {return ops.unidadNot();}
{coma}			  {return ops.unidadComa();}
{asig}			  {return ops.unidadAsig();}
{castInt}		  {return ops.unidadCastInt();}
{castReal}		  {return ops.unidadCastReal();}
[^]                       {errores.errorLexico(fila(),lexema());}  
