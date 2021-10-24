package ch.unisg.tapasstandards.formats;

public abstract class AbstractTaskJsonRepresentation {
    // The media type used for this task representation format
    public static final String MEDIA_TYPE = "application/task+json";

    // A task identifier specific to our implementation (e.g., a UUID). This identifier is then used
    // to generate the task's URI. URIs are standard uniform identifiers and use a universal syntax
    // that can be referenced (and dereferenced) independent of context. In our uniform HTTP API,
    // we identify tasks via URIs and not implementation-specific identifiers.
    public String taskId;

    // A string that represents the task's name
    public String taskName;

    // A string that identifies the task's type. This string could also be a URI (e.g., defined in some
    // Web ontology, as we shall see later in the course), but it's not constrained to be a URI.
    // The task's type can be used to assign executors to tasks, to decide what tasks to bid for, etc.
    public String taskType;

    // The task's status: OPEN, ASSIGNED, RUNNING, or EXECUTED (see Task.Status)
    public String taskStatus;

    // If this task is a delegated task (i.e., a shadow of another task), this URI points to the
    // original task. Because URIs are standard and uniform, we can just dereference this URI to
    // retrieve a representation of the original task.
    public String originalTaskUri;

    // The service provider who executes this task. The service provider is a any string that identifies
    // a TAPAS group (e.g., tapas-group1). This identifier could also be a URI (if we have a good reason
    // for it), but it's not constrained to be a URI.
    public String serviceProvider;

    // A string that provides domain-specific input data for this task. In the context of this project,
    // we can parse and interpret the input data based on the task's type.
    public String inputData;

    // A string that provides domain-specific output data for this task. In the context of this project,
    // we can parse and interpret the output data based on the task's type.
    public String outputData;
}
