### Purpose of this project:

The utility should provide functionality to generate valid IBAN's for different countries. Valid in
this context means syntactically valid according to the ISO standard, but not necessarily
existing bank accounts. At the beginning, we would like to support Germany, Austria and the
Netherlands, but in the future we will extend this list.

The IBAN generator tool must guarantee uniqueness of the generated IBAN's within a
specific instance of the tool - i.e. in-memory storage is sufficient.
We expect this tool to be used as a library (simple jar) by our multiple services. Moreover,
automated tests that run in parallel will concurrently use this library for generating test data.
Therefore, this tool should be thread safe. Implementation should be covered with unit tests
to the level where you consider it to be enough to guarantee the quality of the tool.

### Important Observations:

In this project there are a number of methods and classes that we could continue to implement.
But the idea here was only to show an architecture that meets the problem and that can grow gradually
according to the new needs. Also, show good practices :-)

### How to use this in others projects?
1 - Add this jar or put this in a repository and add the dependence by maven in the other projects:
2 - Call the methods in the same way we called here in the tests.

Example:

    ParametersIban parametersIban = new ParametersIban();
    parametersIban.setCountry(Country.GERMANY);
    parametersIban.setQuantity(1000);
    try {
        List<String> all = IbanContext.generate.IBAN_MULTI.generate(parametersIban);
        all.stream().forEach(item -> System.out.println(item));
    } catch (Exception e){
        fail("Exception was occurred :" + e.getMessage());
    }
