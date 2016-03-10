# cielo-scala
Cliente em Scala para integração com Webservice da Cielo


## Para instalar, basta adicionar no build.sbt:

```scala
libraryDependencies += "com.alugarei" %% "cielo-scala" % "0.4.0"
```

## Utilize da seguinte forma

```scala
val requisicaoSemCaptura = RequisicaoTransacao(
      dadosEC = dadosEC,
      dadosPortador = dadosPortador,
      dadosPedido = dadosPedido,
      formaPagamento = formaPagamento,
      urlRetorno = "http://www.exemplo.com",
      autorizar = AutorizacaoDireta,
      capturar = false)
      
val cielo = new Cielo(Cielo.endPointTest)
val futureTransacao = cielo.enviarRequisicao(requisicaoSemCaptura)
```
